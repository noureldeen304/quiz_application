package com.example.quiz_application.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz_application.Converters.Converter;
import com.example.quiz_application.dto.admin.QuizAdminDTO;
import com.example.quiz_application.model.Category;
import com.example.quiz_application.model.Question;
import com.example.quiz_application.model.Quiz;
import com.example.quiz_application.repositories.CategoryRepository;
import com.example.quiz_application.repositories.QuestionRepository;
import com.example.quiz_application.repositories.QuizRepository;

import jakarta.transaction.Transactional;

@Service
public class QuizService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<QuizAdminDTO> getAllQuizzes() {
        return Converter.convertToDTOs(quizRepository.findAll(), QuizAdminDTO.class);
    }

    public List<QuizAdminDTO> getQuizzesByCategoryName(String category) {
        Category categoryEntity = categoryRepository.findByCategoryName(category)
                .orElseThrow(() -> new IllegalStateException("category"));
        return Converter.convertToDTOs(quizRepository.findByCategory(categoryEntity), QuizAdminDTO.class);
    }

    public void createQuizWithRandomQuestions(String categoryName, Integer noOfQuestions, String version) {
        Category categoryEntity = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(
                        () -> new IllegalStateException("There is no category with this name: " + categoryName));

        quizRepository.findAll().forEach(quiz -> {
            if (Objects.equals(quiz.getVersion(), version)
                    && Objects.equals(quiz.getCategory().getCategoryName(), categoryName)) {
                throw new IllegalStateException("Quiz creation failed: A quiz with the category '" + categoryName +
                        "' and version '" + version + "' already exists. \nPlease use a different version.");
            }
        });

        if (noOfQuestions > categoryEntity.getQuestions().size()) {
            throw new IllegalStateException("Unable to create quiz: Insufficient questions available for category '"
                    + categoryName + "'. \nRequested: " + noOfQuestions + ", Available: "
                    + categoryEntity.getQuestions().size()
                    + ". \nPlease choose a lower number of questions or consider adding more questions to the category.");
        }

        List<Question> listOfRandomQuestions = questionRepository
                .getRandomQuestionsOfSpecificCategory(categoryEntity.getId(), noOfQuestions).get();

        Quiz quiz = new Quiz();
        quiz.setCategory(categoryEntity);
        quiz.setQuestions(listOfRandomQuestions);
        quiz.setVersion(version);
        quiz.setNoOfQuestions(noOfQuestions);
        quizRepository.save(quiz);

    }

    public void createQuizWithSpecificQuestionsIds(String categoryName, String version,
            String stringOfIds) {

        Category categoryEntity = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(
                        () -> new IllegalStateException("There is no category with this name: " + categoryName));

        quizRepository.findAll().forEach(quiz -> {
            if (Objects.equals(quiz.getVersion(), version)
                    && Objects.equals(quiz.getCategory().getCategoryName(), categoryName)) {
                throw new IllegalStateException("Quiz creation failed: A quiz with the category '" + categoryName +
                        "' and version '" + version + "' already exists. \nPlease use a different version.");
            }
        });

        if (stringOfIds == null) {
            throw new IllegalStateException(
                    "Quiz creation failed: provide IDs for questions you want to add to this quiz");
        }
        List<Integer> listOfInsertedIds = parseStringOfIdsToListOfIds(stringOfIds);

        List<Question> listOfQuestionsToBeInserted = new ArrayList<>();

        listOfInsertedIds.forEach(insertedId -> {
            Question questionEntity = questionRepository.findById(insertedId)
                    .orElseThrow(
                            () -> new IllegalStateException(
                                    "Question with id (" + insertedId + ") doesn't exist."));

            if (!categoryEntity.getQuestionsIds().contains(insertedId)) {
                throw new IllegalStateException(
                        "This category (" + categoryName + ") doesn't have a question with id (" + insertedId + ")");
            }

            listOfQuestionsToBeInserted.add(questionEntity);
        });

        Quiz quiz = new Quiz();
        quiz.setCategory(categoryEntity);
        quiz.setVersion(version);
        quiz.setNoOfQuestions(listOfInsertedIds.size());
        quiz.setQuestions(listOfQuestionsToBeInserted);
        quizRepository.save(quiz);

    }

    @Transactional
    public void updateVersionOfQuiz(Integer id, String version) {
        Quiz quizEntity = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Quiz with id (" + id + ") doesn't exist."));

        Category categoryEntity = categoryRepository.findByCategoryName(quizEntity.getCategory().getCategoryName())
                .get();

        quizRepository.findAll().forEach(quiz -> {
            if (Objects.equals(quiz.getVersion(), version)) {

                throw new IllegalStateException(
                        "Quiz updating failed: A quiz with this category '" + categoryEntity.getCategoryName() +
                                "' and this version '" + version
                                + "' already exists. \nPlease use a different version.");
            }
        });
        quizEntity.setVersion(version);
    }

    @Transactional
    public void addSpecificNumberOfRandomQuestionsToQuiz(Integer id, Integer noOfQuestions) {
        Quiz quizEntity = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Question with id (" + id + ") doesn't exist."));

        if (noOfQuestions != null && noOfQuestions > 0) {

            Category categoryEntity = categoryRepository.findByCategoryName(quizEntity.getCategory().getCategoryName())
                    .get();

            List<Question> listOfQuestionsInCategoryEntityNotInQuizEntity = categoryEntity.getQuestions().stream()
                    .filter(question -> !quizEntity.getQuestions().contains(question))
                    .collect(Collectors.toList());

            quizEntity.getQuestions().addAll(listOfQuestionsInCategoryEntityNotInQuizEntity.subList(0, noOfQuestions));
            quizEntity.setNoOfQuestions(quizEntity.getQuestions().size());

        } else {
            throw new IllegalStateException("Invalid number of questions");
        }
    }

    @Transactional
    public void removeSpecificNumberOfRandomQuestionsFromQuiz(Integer id, Integer noOfQuestions) {
        Quiz quizEntity = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Question with id (" + id + ") doesn't exist."));

        if (noOfQuestions != null && noOfQuestions > 0) {

            quizEntity.getQuestions().subList(0, noOfQuestions).clear();
            quizEntity.setNoOfQuestions(quizEntity.getQuestions().size());

        } else {
            throw new IllegalStateException("Invalid number of questions");
        }
    }

    @Transactional
    public void addSpecificNumberOfSelectedQuestionsToQuiz(Integer id, String ids) {

        Quiz quizEntity = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Quiz with id (" + id + ") doesn't exist."));

        if (ids != null && ids.length() > 0) {

            List<Integer> listOfIds = parseStringOfIdsToListOfIds(ids);

            listOfIds.forEach(insertedId -> {
                questionRepository.findById(insertedId).orElseThrow(
                        () -> new IllegalStateException("Question with id (" + insertedId + ") doesn't exist."));
            });

            List<Integer> questionsIdsAlreadyExistInQuiz = listOfIds.stream()
                    .filter(insertedId -> quizEntity.getQuestionsIds().contains(insertedId)).toList();

            if (!questionsIdsAlreadyExistInQuiz.isEmpty()) {
                throw new IllegalStateException("Question adding failed: A quiz with id " + id
                        + " already has a question with id " + questionsIdsAlreadyExistInQuiz.toString());
            }

            Category categoryEntity = categoryRepository.findByCategoryName(quizEntity.getCategory().getCategoryName())
                    .get();

            List<Integer> invalidIds = listOfIds.stream()
                    .filter(insertedId -> !categoryEntity.getQuestionsIds().contains(insertedId)).toList();

            if (!invalidIds.isEmpty()) {
                throw new IllegalStateException(
                        "These IDs: " + invalidIds.toString() + " don't exist in " + categoryEntity.getCategoryName()
                                + " category");
            }

            for (Integer insertedId : listOfIds) {
                Question question = questionRepository.findById(insertedId)
                        .orElseThrow(
                                () -> new IllegalStateException("Quiz with id (" + insertedId + ") doesn't exist."));

                quizEntity.getQuestions().add(question);
            }
            quizEntity.setNoOfQuestions(quizEntity.getQuestions().size());
        }
    }

    @Transactional
    public void removeSpecificNumberOfSelectedQuestionsFromQuiz(Integer id, String ids) {
        Quiz quizEntity = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Quiz with id (" + id + ") doesn't exist."));
        List<Integer> listOfIds = parseStringOfIdsToListOfIds(ids);

        listOfIds.forEach(insertedId -> {
            if (!quizEntity.getQuestionsIds().contains(insertedId)) {
                throw new IllegalStateException(
                        "A quiz with id (" + id + ") doesn't have a question with this id (" + insertedId + ")");
            }
        });

        listOfIds.forEach(insertedId -> {
            Question question = questionRepository.findById(insertedId).get();
            quizEntity.getQuestions().remove(question);
        });

        quizEntity.setNoOfQuestions(quizEntity.getQuestions().size());
    }

    @Transactional
    public void removeAllQuestionsFromQuizById(Integer id) {
        Quiz quizEntity = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Question with id (" + id + ") doesn't exist."));
        quizEntity.getQuestions().removeAll(quizEntity.getQuestions());
        quizEntity.setNoOfQuestions(quizEntity.getQuestions().size());
    }

    public void removeQuizById(Integer id) {
        if (!quizRepository.findById(id).isPresent()) {
            throw new IllegalStateException(
                    "Question with id (" + id + ") doesn't exist.");
        }
        quizRepository.deleteById(id);
    }

    private List<Integer> parseStringOfIdsToListOfIds(String ids) {
        try {
            return Arrays.stream(ids.split(","))
                    .map(String::trim)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            String invalidInput = e.getMessage().replaceAll("For input string: ", "");
            throw new IllegalStateException("Invalid ID format: \"" + invalidInput
                    + "\". \nPlease provide numerical values separated by commas for the 'ids' parameter.");
        }
    }

}