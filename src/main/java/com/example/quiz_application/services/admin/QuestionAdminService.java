package com.example.quiz_application.services.admin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz_application.config.exceptions.AnswerNotFoundInOptionsException;
import com.example.quiz_application.config.exceptions.CategoryNotFoundException;
import com.example.quiz_application.config.exceptions.DifficultyLevelNotFoundException;
import com.example.quiz_application.config.exceptions.OptionIsRightAnswerException;
import com.example.quiz_application.config.exceptions.QuestionAlreadyExistsException;
import com.example.quiz_application.config.exceptions.QuestionNotFoundException;
import com.example.quiz_application.dto.admin.QuestionAdminDTO;
import com.example.quiz_application.enums.DifficultyLevel;
import com.example.quiz_application.mapper.Mapper;
import com.example.quiz_application.model.Category;
import com.example.quiz_application.model.Question;
import com.example.quiz_application.repositories.CategoryRepository;
import com.example.quiz_application.repositories.QuestionRepository;

import jakarta.transaction.Transactional;

@Service
public class QuestionAdminService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<QuestionAdminDTO> getAllQuestions() {
        return mapper.mapList(questionRepository.findAll(), QuestionAdminDTO.class);
    }

    public List<QuestionAdminDTO> getQuestionsByCategory(String category) {
        Category categoryFromDB = categoryRepository.findByCategoryName(category)
                .orElseThrow(() -> new CategoryNotFoundException(category));
        return mapper.mapList(categoryFromDB.getQuestions(), QuestionAdminDTO.class);
    }

    public List<QuestionAdminDTO> getQuestionsBydifficultyLevel(String difficultyLevel) {
        ensureDifficultyLevelIsValid(difficultyLevel);
        List<Question> listOfQuestions = questionRepository
                .findByDifficultyLevel(DifficultyLevel.valueOf(difficultyLevel.toUpperCase())).get();

        return mapper.mapList(listOfQuestions, QuestionAdminDTO.class);
    }

    public QuestionAdminDTO getQuestionById(Integer id) {
        questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
        Question questionFromDB = questionRepository.findById(id).get();

        return mapper.map(questionFromDB, QuestionAdminDTO.class);
    }

    @Transactional
    public void addNewQuestion(QuestionAdminDTO questionAdminDTO) {
        Optional<Question> optional = questionRepository.findByQuestionTitle(questionAdminDTO.getQuestionTitle());
        if (optional.isPresent())
            throw new QuestionAlreadyExistsException();

        else {

            ensureDifficultyLevelIsValid(questionAdminDTO.getDifficultyLevel());

            if (!questionAdminDTO.getOptions().contains(questionAdminDTO.getAnswer())) {
                throw new AnswerNotFoundInOptionsException();
            }

            if (!categoryRepository.findByCategoryName(questionAdminDTO.getCategoryName()).isPresent()) {
                categoryRepository.save(new Category(questionAdminDTO.getCategoryName()));
            }

            Question questionEntity = mapper.map(questionAdminDTO, Question.class);

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

        Question questionFromDB = questionRepository.findById(id)
                .orElseThrow(
                        () -> new QuestionNotFoundException(id));

        // ================== QuestionTitle ============================
        if (!isNullOrEmpty(questionTitle)) {

            if (questionRepository.findByQuestionTitle(questionTitle).isPresent())
                throw new QuestionAlreadyExistsException();

            questionFromDB.setQuestionTitle(questionTitle);
        }

        // ================== CategoryName ============================
        if (!isNullOrEmpty(categoryName)) {
            Category categoryEntity = categoryRepository.findByCategoryName(categoryName)
                    .orElseThrow(() -> new CategoryNotFoundException(categoryName));
            questionFromDB.setCategory(categoryEntity);
        }

        // ================== DifficultyLevel ============================
        if (!isNullOrEmpty(difficultyLevel)) {
            ensureDifficultyLevelIsValid(difficultyLevel);
            questionFromDB
                    .setDifficultyLevel(DifficultyLevel.valueOf(difficultyLevel.toUpperCase()));
        }

        // ================== Options & Answer ============================
        if (!isNullOrEmpty(option1) && !isNullOrEmpty(option2)
                && !!isNullOrEmpty(option3) && !isNullOrEmpty(option4)
                && !isNullOrEmpty(answer)) {

            if (!Arrays.asList(option1, option2, option3, option4).contains(answer)) {
                throw new AnswerNotFoundInOptionsException();
            }
            questionFromDB.setOption1(option1);
            questionFromDB.setOption2(option2);
            questionFromDB.setOption3(option3);
            questionFromDB.setOption4(option4);
            questionFromDB.setAnswer(answer);
        } else {

            Map<String, String> exptectedListOfOptions = new HashMap<>(4);

            if (!isNullOrEmpty(option1)) {
                if (questionFromDB.getOption1().equals(questionFromDB.getAnswer()) && isNullOrEmpty(answer)) {
                    throw new OptionIsRightAnswerException(questionFromDB.getOption1(), 1);
                }
                if (!isNullOrEmpty(answer) && !Objects.equals(option1, answer)) {
                    throw new AnswerNotFoundInOptionsException();
                }
                exptectedListOfOptions.put("option1", option1);
            } else {
                exptectedListOfOptions.put("option1", questionFromDB.getOption1());
            }

            if (!isNullOrEmpty(option2)) {
                if (questionFromDB.getOption2().equals(questionFromDB.getAnswer()) && isNullOrEmpty(answer)) {
                    throw new OptionIsRightAnswerException(questionFromDB.getOption2(), 2);
                }
                if (!isNullOrEmpty(answer) && !Objects.equals(option2, answer)) {
                    throw new AnswerNotFoundInOptionsException();
                }
                exptectedListOfOptions.put("option2", option2);
            } else {
                exptectedListOfOptions.put("option2", questionFromDB.getOption2());
            }

            if (!isNullOrEmpty(option3)) {
                if (questionFromDB.getOption3().equals(questionFromDB.getAnswer()) && isNullOrEmpty(answer)) {
                    throw new OptionIsRightAnswerException(questionFromDB.getOption3(), 3);
                }
                if (!isNullOrEmpty(answer) && !Objects.equals(option3, answer)) {
                    throw new AnswerNotFoundInOptionsException();
                }
                exptectedListOfOptions.put("option3", option3);
            } else {
                exptectedListOfOptions.put("option3", questionFromDB.getOption3());
            }

            if (!isNullOrEmpty(option4)) {
                if (questionFromDB.getOption4().equals(questionFromDB.getAnswer()) && isNullOrEmpty(answer)) {
                    throw new OptionIsRightAnswerException(questionFromDB.getOption4(), 4);
                }
                if (!isNullOrEmpty(answer) && !Objects.equals(option4, answer)) {
                    throw new AnswerNotFoundInOptionsException();
                }
                exptectedListOfOptions.put("option4", option4);
            } else {
                exptectedListOfOptions.put("option4", questionFromDB.getOption4());
            }

            if (!isNullOrEmpty(answer)) {
                if (!exptectedListOfOptions.values().stream().toList().contains(answer)) {
                    throw new AnswerNotFoundInOptionsException();
                }
                questionFromDB.setAnswer(answer);
            }

            questionFromDB.setOption1(exptectedListOfOptions.get("option1"));
            questionFromDB.setOption2(exptectedListOfOptions.get("option2"));
            questionFromDB.setOption3(exptectedListOfOptions.get("option3"));
            questionFromDB.setOption4(exptectedListOfOptions.get("option4"));
        }

        return mapper.map(questionFromDB, QuestionAdminDTO.class);
    }

    public QuestionAdminDTO removeQuestion(Integer id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
        questionRepository.deleteById(id);
        return mapper.map(question, QuestionAdminDTO.class);
    }

    // ======================================================
    private void ensureDifficultyLevelIsValid(String insertedDifficultyLevel) {
        try {
            DifficultyLevel.valueOf(insertedDifficultyLevel.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DifficultyLevelNotFoundException(insertedDifficultyLevel);

        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
