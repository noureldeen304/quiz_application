package com.example.quiz_application.services;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz_application.Converters.Converter;
import com.example.quiz_application.dto.admin.QuestionAdminDTO;
import com.example.quiz_application.enums.DifficultyLevel;
import com.example.quiz_application.model.Category;
import com.example.quiz_application.model.Question;
import com.example.quiz_application.repositories.CategoryRepository;
import com.example.quiz_application.repositories.QuestionRepository;

import jakarta.transaction.Transactional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private final String availableDifficultyLevel = Arrays.toString(DifficultyLevel.values());

    public List<QuestionAdminDTO> getAllQuestions() {
        return Converter.convertToDTOs(questionRepository.findAll(), QuestionAdminDTO.class);
    }

    public List<QuestionAdminDTO> getQuestionsByCategory(String category) {
        Category categoryFromDB = categoryRepository.findByCategoryName(category)
                .orElseThrow(() -> new IllegalStateException("There is no category" + " named: " + category));
        return Converter.convertToDTOs(categoryFromDB.getQuestions(), QuestionAdminDTO.class);
    }

    public List<QuestionAdminDTO> getQuestionsBydifficultyLevel(String difficultyLevel) {
        ensureDifficultyLevelIsValid(difficultyLevel);
        List<Question> listOfQuestions = questionRepository
                .findByDifficultyLevel(DifficultyLevel.valueOf(difficultyLevel.toUpperCase())).get();

        return Converter.convertToDTOs(listOfQuestions, QuestionAdminDTO.class);
    }

    public QuestionAdminDTO getQuestionById(Integer id) {
        questionRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("There is no question with id: " + id));
        Question questionFromDB = questionRepository.findById(id).get();

        return Converter.convertToDTO(questionFromDB, QuestionAdminDTO.class);
    }

    @Transactional
    public void addNewQuestion(QuestionAdminDTO questionAdminDTO) {
        Optional<Question> optional = questionRepository.findByQuestionTitle(questionAdminDTO.getQuestionTitle());
        if (optional.isPresent())
            throw new IllegalStateException("This question already exits.");

        else {

            ensureDifficultyLevelIsValid(questionAdminDTO.getDifficultyLevel());
            ensureAnswerIsChosenFromProvidedInsertedOptions(questionAdminDTO.getOptions(),
                    questionAdminDTO.getAnswer());

            if (!categoryRepository.findByCategoryName(questionAdminDTO.getCategoryName()).isPresent()) {
                categoryRepository.save(new Category(questionAdminDTO.getCategoryName()));
            }

            Question questionEntity = Converter.convertToDAO(questionAdminDTO, Question.class);

            Category categoryEntity = categoryRepository.findByCategoryName(questionAdminDTO.getCategoryName()).get();
            questionEntity.setCategory(categoryEntity);
            questionRepository.save(questionEntity);
        }
    }

    @Transactional
    public QuestionAdminDTO updateQuestion(Integer id,
            String categoryName,
            String difficultyLevel,
            String questionTitle,
            String option1,
            String option2,
            String option3,
            String option4,
            String answer) {

        QuestionAdminDTO questionAdminDTO = QuestionAdminDTO.builder()
                .id(id)
                .categoryName(categoryName)
                .difficultyLevel(difficultyLevel)
                .questionTitle(questionTitle)
                .option1(option1)
                .option2(option2)
                .option3(option3)
                .option4(option4)
                .answer(answer).build();

        Question questionFromDB = questionRepository.findById(questionAdminDTO.getId())
                .orElseThrow(
                        () -> new IllegalStateException("There is no question with id: " + questionAdminDTO.getId()));

        // ================== QuestionTitle ============================
        if (!isNullOrEmpty(questionAdminDTO.getQuestionTitle())) {

            ensureFieldHasDifferentValueInDB("QuestionTitle", questionAdminDTO.getQuestionTitle(),
                    questionFromDB.getQuestionTitle());
            if (questionRepository.findByQuestionTitle(questionAdminDTO.getQuestionTitle()).isPresent())
                throw new IllegalStateException("This question title already exists for another question.");

            questionFromDB.setQuestionTitle(questionAdminDTO.getQuestionTitle());
        }

        // ================== CategoryName ============================
        if (!isNullOrEmpty(questionAdminDTO.getCategoryName())) {

            ensureFieldHasDifferentValueInDB("CategoryName", questionAdminDTO.getCategoryName(),
                    questionFromDB.getCategory().getCategoryName());
            Category categoryEntity = categoryRepository.findByCategoryName(questionAdminDTO.getCategoryName())
                    .orElseThrow(() -> new IllegalStateException(
                            "There is no category" + " named: " + questionAdminDTO.getCategoryName()));
            questionFromDB.setCategory(categoryEntity);
        }

        // ================== DifficultyLevel ============================
        if (!isNullOrEmpty(questionAdminDTO.getDifficultyLevel())) {

            ensureDifficultyLevelIsValid(questionAdminDTO.getDifficultyLevel());
            ensureFieldHasDifferentValueInDB("DifficultyLevel", questionAdminDTO.getDifficultyLevel(),
                    questionFromDB.getDifficultyLevel().toString());
            questionFromDB
                    .setDifficultyLevel(DifficultyLevel.valueOf(questionAdminDTO.getDifficultyLevel().toUpperCase()));
        }

        // ================== Question Options & Answer ============================
        if (questionAdminDTO.getOption1() != null && questionAdminDTO.getOption2() != null
                && questionAdminDTO.getOption3() != null && questionAdminDTO.getOption4() != null
                && questionAdminDTO.getAnswer() != null) {
            if (!questionAdminDTO.getOptions().contains(questionAdminDTO.getAnswer())) {
                throw new IllegalStateException(
                        "The answer must be chosen from the available options for the question.");
            }
            questionFromDB.setOption1(questionAdminDTO.getOption1());
            questionFromDB.setOption2(questionAdminDTO.getOption2());
            questionFromDB.setOption3(questionAdminDTO.getOption3());
            questionFromDB.setOption4(questionAdminDTO.getOption4());
            questionFromDB.setAnswer(questionAdminDTO.getAnswer());
        } else {

            validateOptionValues(questionAdminDTO, questionFromDB);

            if (!isNullOrEmpty(questionAdminDTO.getAnswer())) {
                ensureFieldHasDifferentValueInDB("answer", questionAdminDTO.getAnswer(), questionFromDB.getAnswer());
                ensureAnswerIsChosenFromProvidedOptionsInDB(questionFromDB.getOptions(), questionAdminDTO.getAnswer());
                questionFromDB.setAnswer(questionAdminDTO.getAnswer());
            }
        }

        return Converter.convertToDTO(questionFromDB, QuestionAdminDTO.class);
    }

    public void removeQuestion(Integer id) {
        questionRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("There is no question with id: " + id));
        questionRepository.deleteById(id);
    }

    public void ensureDifficultyLevelIsValid(String insertedDifficultyLevel) {
        try {
            DifficultyLevel.valueOf(insertedDifficultyLevel.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("There is no difficulty level" + " named: " + insertedDifficultyLevel
                    + "\nAvailable values: " + availableDifficultyLevel);

        }
    }

    private void validateOptionValues(QuestionAdminDTO questionAdminDTO,
            Question questionInDB) {
        int index = 0;
        String valueOfTheOptionInDB;
        List<String> valuesOfTheOtherInsertedOptionValues;
        List<String> valuesOfTheOtherOptionsInDB;
        for (String insertedOptionValue : questionAdminDTO.getOptions()) {
            insertedOptionValue = questionAdminDTO.getOptions().get(index);
            valueOfTheOptionInDB = questionInDB.getOptions().get(index);
            valuesOfTheOtherInsertedOptionValues = questionAdminDTO.getOptionsWithoutOption(insertedOptionValue);
            valuesOfTheOtherOptionsInDB = questionInDB.getOptions();
            if (!isNullOrEmpty(insertedOptionValue)) {
                ensureOptionIsNotTheAnswer("option" + (index + 1), valueOfTheOptionInDB, questionInDB.getAnswer());
                ensureFieldHasDifferentValueInDB("option" + (index + 1), insertedOptionValue, valueOfTheOptionInDB);
                checkForDuplicateOptionValue(insertedOptionValue, valuesOfTheOtherInsertedOptionValues);
                ensureOptionValueIsUniqueInDB("option" + (index + 1), insertedOptionValue, valuesOfTheOtherOptionsInDB);
                questionInDB.setValueForOption(index + 1, insertedOptionValue);
            }
            index++;
        }
    }

    private void ensureAnswerIsChosenFromProvidedInsertedOptions(List<String> valuesOfOptionsUserInputs,
            String answer) {
        if (!valuesOfOptionsUserInputs.contains(answer)) {
            throw new IllegalStateException("The answer must be chosen from the available options you provided: "
                    + valuesOfOptionsUserInputs.toString());
        }
    }

    private void ensureAnswerIsChosenFromProvidedOptionsInDB(List<String> valuesOfOptionsInDB, String answer) {
        if (!valuesOfOptionsInDB.contains(answer)) {
            throw new IllegalStateException("The answer must be chosen from the available options in the database: "
                    + valuesOfOptionsInDB.toString());
        }
    }

    private void ensureOptionIsNotTheAnswer(String optionName, String optionValue, String answerValue) {
        if (Objects.equals(optionValue, answerValue)) {
            throw new IllegalStateException(
                    optionName
                            + "\" you're trying to modify is the right answer; you cannot modify it until you change the right answer.");
        }
    }

    private void ensureFieldHasDifferentValueInDB(String fieldName, String insertedValue,
            String existingValueInDB) {

        if (Objects.equals(insertedValue, existingValueInDB)) {
            throw new IllegalStateException("The provided value: \"" + insertedValue
                    + "\" is already assigned to \"" + fieldName + "\" in the database.");
        }
    }

    private void checkForDuplicateOptionValue(String insertedOptionValue,
            List<String> valuesOfTheOtherInsertedOptionValues) {

        if (valuesOfTheOtherInsertedOptionValues.contains(insertedOptionValue)) {
            throw new IllegalStateException(
                    "Duplicate option value (" + insertedOptionValue
                            + ") found; each option must have a unique value.");
        }
    }

    private void ensureOptionValueIsUniqueInDB(String optionName, String insertedOptionValue,
            List<String> valuesOfTheOtherOptionsInDB) {

        if (valuesOfTheOtherOptionsInDB.contains(insertedOptionValue)) {
            Integer indexOfTheOptionHasTheSameValue = valuesOfTheOtherOptionsInDB.indexOf(insertedOptionValue);
            throw new IllegalStateException("The provided value \"" + insertedOptionValue
                    + "\" you are trying to assign to \"" + optionName + "\""
                    + "\" is already assigned to \"option" + (indexOfTheOptionHasTheSameValue + 1)
                    + "\" in the database.\nEach option must have a unique value.");
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
