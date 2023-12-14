package com.example.quiz_application.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.quiz_application.Converters.Converter;
import com.example.quiz_application.dto.admin.QuizAdminDTO;
import com.example.quiz_application.exceptions.InvalidInputDataException;
import com.example.quiz_application.model.Category;
import com.example.quiz_application.model.Question;
import com.example.quiz_application.model.Quiz;
import com.example.quiz_application.repositories.CategoryRepository;
import com.example.quiz_application.repositories.QuestionRepository;
import com.example.quiz_application.repositories.QuizRepository;

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

    public void createQuizRandomly(String categoryName, Integer noOfQuestions, String version) {
        Category categoryEntity = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(
                        () -> new InvalidInputDataException("There is no category with this name: " + categoryName));

        if (noOfQuestions > categoryEntity.getQuestions().size()) {
            throw new InvalidInputDataException("Unable to create quiz: Insufficient questions available for category '"
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

        try {
            quizRepository.save(quiz);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("uk7kppata8n46wpvuwc9cx5o5d3")) {

                throw new InvalidInputDataException("Quiz creation failed: A quiz with the category '" + categoryName +
                        "' and version '" + version + "' already exists. \nPlease use a different version.");
            }
        }

    }

    public void createQuizWithSpecificQuestionsIds(String categoryName, String version,
            String stringOfIds) {

        List<Integer> listOfIds = Arrays.stream(stringOfIds.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Category categoryEntity = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(
                        () -> new InvalidInputDataException("There is no category with this name: " + categoryName));
        List<Integer> listOfIdsOfQuestionsOfThisCatogery = categoryEntity.getQuestions().stream()
                .map(question -> question.getId()).collect(Collectors.toList());

        List<Question> listOfQuestions = new ArrayList<>();

        for (Integer insertedId : listOfIds) {

            if (!listOfIdsOfQuestionsOfThisCatogery.contains(insertedId)) {
                throw new InvalidInputDataException(
                        "This category (" + categoryName + ") doesn't have a question with id (" + insertedId + ")");
            }

            Question questionEntity = questionRepository.findById(insertedId)
                    .orElseThrow(
                            () -> new InvalidInputDataException(
                                    "Question with id (" + insertedId + ") doesn't exist."));
            listOfQuestions.add(questionEntity);
        }

        if (listOfIds.size() > categoryEntity.getQuestions().size()) {
            throw new InvalidInputDataException("Unable to create quiz: Insufficient questions available for category '"
                    + categoryName + "'. \nRequested: " + listOfIds.size() + ", Available: "
                    + categoryEntity.getQuestions().size()
                    + ". \nPlease choose a lower number of questions or consider adding more questions to the category.");
        }

        Quiz quiz = new Quiz();
        quiz.setCategory(categoryEntity);
        quiz.setVersion(version);
        quiz.setNoOfQuestions(listOfIds.size());
        quiz.setQuestions(listOfQuestions);

        try {
            quizRepository.save(quiz);

        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("uk7kppata8n46wpvuwc9cx5o5d3")) {
                throw new InvalidInputDataException("Quiz creation failed: A quiz with the category '" + categoryName +
                        "' and version '" + version + "' already exists. \nPlease use a different version.");
            }
        }
    }

    public void removeQuizById(Integer id) {
        if (!quizRepository.findById(id).isPresent()) {
            throw new InvalidInputDataException(
                    "Question with id (" + id + ") doesn't exist.");
        }
        quizRepository.deleteById(id);
    }

    // public QuizForUser getQuizByCategoryAndVersionForUser(String category, String
    // version) {
    // Optional<Quiz> optional = quizRepository.findByCategoryAndVersion(category,
    // version);
    // if (optional.isPresent()) {

    // Quiz quiz = optional.get();
    // QuizForUser quizForUser = new QuizForUser();

    // quizForUser.setId(quiz.getId());
    // quizForUser.setCategory(quiz.getCategory());
    // quizForUser.setVersion(quiz.getVersion());

    // for (Question question : quiz.getQuestions()) {
    // QuestionForUser questionForUser = new QuestionForUser();
    // questionForUser.setId(question.getId());
    // questionForUser.setQuestion_title(question.getQuestion_title());
    // questionForUser.setOption1(question.getOption1());
    // questionForUser.setOption2(question.getOption2());
    // questionForUser.setOption3(question.getOption3());
    // questionForUser.setOption4(question.getOption4());

    // quizForUser.getQuestions().add(questionForUser);
    // }

    // return quizForUser;
    // }
    // throw new RuntimeException(new IllegalAccessError("There is no quiz in this
    // category with this version"));
    // }

    // public Quiz getQuizByCategoryAndVersionForAdmin(String category, String
    // version) {
    // Optional<Quiz> optional = quizRepository.findByCategoryAndVersion(category,
    // version);
    // if (optional.isPresent()) {
    // Quiz quiz = optional.get();
    // return quiz;
    // }
    // throw new RuntimeException(new IllegalAccessError("There is no quiz in this
    // category with this version"));
    // }

    // public String submitAnswers(List<Answer> answers, String category, String
    // version) {
    // Optional<Quiz> optional = quizRepository.findByCategoryAndVersion(category,
    // version);
    // Integer userScore = null;
    // Integer totalScore = null;
    // if (optional.isPresent()) {
    // Quiz quiz = optional.get();
    // Map<Integer, String> quizAnswersMap = new HashMap<>();

    // for (Question question : quiz.getQuestions()) {
    // quizAnswersMap.put(question.getId(), question.getRight_answer());
    // }
    // userScore = 0;
    // totalScore = quiz.getNoOfQuestions();
    // // score calculation
    // for (Answer answer : answers) {
    // String ans = quizAnswersMap.get(answer.getId());
    // if (answer.getAnswer().equals(ans)) {
    // userScore++;
    // }
    // }
    // }
    // if (userScore != null && totalScore != null) {
    // return "your score: " + userScore + " out of " + totalScore;
    // }
    // throw new RuntimeException(new IllegalAccessError("There is no quiz in this
    // category with this version"));
    // }
}