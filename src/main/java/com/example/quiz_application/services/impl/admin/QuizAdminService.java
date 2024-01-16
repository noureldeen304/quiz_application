package com.example.quiz_application.services.impl.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.quiz_application.config.exceptions.CategoryHasInsufficientQuestionsException;
import com.example.quiz_application.config.exceptions.CategoryNotFoundException;
import com.example.quiz_application.config.exceptions.IdsNotSelectedException;
import com.example.quiz_application.config.exceptions.InvalidIdsException;
import com.example.quiz_application.config.exceptions.QuestionAlreadyExistsException;
import com.example.quiz_application.config.exceptions.QuestionNotFoundException;
import com.example.quiz_application.config.exceptions.QuizNotFoundException;
import com.example.quiz_application.config.exceptions.QuizNotFoundInYourQuizzesException;
import com.example.quiz_application.config.exceptions.QuizNotTakenByStudentException;
import com.example.quiz_application.config.exceptions.UserNotFoundException;
import com.example.quiz_application.config.exceptions.QuizAlreadyExistsException;
import com.example.quiz_application.dto.admin.ModelAnswerDTO;
import com.example.quiz_application.dto.admin.QuizAdminDTO;
import com.example.quiz_application.dto.admin.QuizScoreAdminDTO;
import com.example.quiz_application.mapper.Mapper;
import com.example.quiz_application.model.Admin;
import com.example.quiz_application.model.Category;
import com.example.quiz_application.model.Question;
import com.example.quiz_application.model.Quiz;
import com.example.quiz_application.model.QuizScore;
import com.example.quiz_application.repositories.AdminRepository;
import com.example.quiz_application.repositories.CategoryRepository;
import com.example.quiz_application.repositories.QuestionRepository;
import com.example.quiz_application.repositories.QuizRepository;
import com.example.quiz_application.repositories.QuizScoreRepository;
import com.example.quiz_application.services.interfaces.admin.IQuizAdminService;

import jakarta.transaction.Transactional;
import lombok.NonNull;

@Service
public class QuizAdminService implements IQuizAdminService {

        @Autowired
        private Mapper mapper;

        @Autowired
        private QuestionRepository questionRepository;

        @Autowired
        private QuizRepository quizRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        @Autowired
        private AdminRepository adminRepository;

        @Autowired
        private QuizScoreRepository quizScoreRepository;

        private final String ROLE = "Admin";

        public List<QuizAdminDTO> getAllOfQuizzes() {

                return mapper.mapList(quizRepository.findAll(), QuizAdminDTO.class)
                                .stream()
                                .sorted(Comparator.comparing(QuizAdminDTO::getId))
                                .collect(Collectors.toList());
        }

        public List<QuizAdminDTO> getAllOfAdminQuizzes() {
                String username = getAuthentication().getName();
                Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new UserNotFoundException(ROLE, username));
                return mapper.mapList(quizRepository.findByAdmin(admin).get(), QuizAdminDTO.class)
                                .stream()
                                .sorted(Comparator.comparing(QuizAdminDTO::getId))
                                .collect(Collectors.toList());

        }

        public QuizAdminDTO getQuizById(@NonNull Integer id) {
                Quiz quizEntity = quizRepository.findById(id)
                                .orElseThrow(() -> new QuizNotFoundException(id));
                return mapper.map(quizEntity, QuizAdminDTO.class);
        }

        public List<QuizAdminDTO> getQuizzesByCategoryName(String categoryName) {

                Category categoryEntity = categoryRepository.findByCategoryName(capitalizeFirstLetter(categoryName))
                                .orElseThrow(() -> new CategoryNotFoundException(categoryName));

                List<Quiz> quizs = quizRepository.findByCategory(categoryEntity)
                                .orElseThrow(() -> new QuizNotFoundException(categoryName));

                return mapper.mapList(quizs, QuizAdminDTO.class)
                                .stream()
                                .sorted(Comparator.comparing(QuizAdminDTO::getId))
                                .collect(Collectors.toList());

        }

        public QuizAdminDTO getQuizzesByCategoryNameAndVersion(String categoryName, String version) {

                Category categoryEntity = categoryRepository.findByCategoryName(capitalizeFirstLetter(categoryName))
                                .orElseThrow(() -> new CategoryNotFoundException(categoryName));

                Quiz quizEntity = quizRepository
                                .findByCategoryAndVersion(categoryEntity, version.toUpperCase())
                                .orElseThrow(() -> new QuizNotFoundException(categoryName, version.toUpperCase()));

                return mapper.map(quizEntity, QuizAdminDTO.class);
        }

        public List<QuizScoreAdminDTO> getStudentScoresInQuiz(Integer id) {
                String username = getAuthentication().getName();
                Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new UserNotFoundException(ROLE, username));
                Quiz quizEntity = quizRepository.findByIdAndAdmin(id, admin)
                                .orElseThrow(() -> new QuizNotFoundInYourQuizzesException(id));

                List<QuizScore> quizScores = quizScoreRepository.findByQuiz(quizEntity)
                                .orElseThrow(() -> new QuizNotTakenByStudentException(id));

                return mapper.mapList(quizScores, QuizScoreAdminDTO.class);
        }

        public List<ModelAnswerDTO> getQuizModelAnswers(Integer id) {

                String username = getAuthentication().getName();
                Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new UserNotFoundException(ROLE, username));

                Quiz quizEntity = quizRepository.findByIdAndAdmin(id, admin)
                                .orElseThrow(() -> new QuizNotFoundInYourQuizzesException(id));
                List<ModelAnswerDTO> answerSubmissionDTOs = mapper.mapList(quizEntity.getQuestions(),
                                ModelAnswerDTO.class);
                return answerSubmissionDTOs;
        }

        public QuizAdminDTO createQuizWithRandomQuestions(String categoryName, Integer noOfQuestions, String version) {

                Category categoryEntity = categoryRepository.findByCategoryName(capitalizeFirstLetter(categoryName))
                                .orElseThrow(() -> new CategoryNotFoundException(categoryName));

                String username = getAuthentication().getName();
                Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new UserNotFoundException(ROLE, username));
                admin.getQuizzes().forEach(quiz -> {
                        if (Objects.equals(quiz.getVersion(), version.toUpperCase())
                                        && Objects.equals(quiz.getCategory().getCategoryName(),
                                                        capitalizeFirstLetter(categoryName))) {
                                throw new QuizAlreadyExistsException(categoryName, version.toUpperCase(), quiz.getId());
                        }
                });

                if (noOfQuestions > categoryEntity.getQuestions().size()) {
                        throw new CategoryHasInsufficientQuestionsException(categoryName, noOfQuestions,
                                        categoryEntity.getQuestions().size());
                }

                List<Question> listOfRandomQuestions = questionRepository
                                .getRandomQuestionsOfSpecificCategory(categoryEntity.getId(), noOfQuestions).get();

                Quiz quiz = new Quiz();
                quiz.setCategory(categoryEntity);
                quiz.setQuestions(listOfRandomQuestions);
                quiz.setVersion(version.toUpperCase());
                quiz.setNoOfQuestions(noOfQuestions);
                quiz.setAdmin(admin);
                quizRepository.save(quiz);

                // Retrieve the quiz to verify successful storage and availability for retrieval
                Quiz quizEntity = quizRepository
                                .findByAdminAndCategoryAndVersion(admin, categoryEntity, version.toUpperCase())
                                .orElseThrow(() -> new QuizNotFoundException(categoryName, version.toUpperCase()));
                return mapper.map(quizEntity, QuizAdminDTO.class);
        }

        public QuizAdminDTO createQuizWithSelectedQuestionsIds(String categoryName, String version,
                        String stringOfIds) {

                if (isNullOrEmpty(stringOfIds)) {
                        throw new IdsNotSelectedException();
                }

                Category categoryEntity = categoryRepository.findByCategoryName(capitalizeFirstLetter(categoryName))
                                .orElseThrow(
                                                () -> new CategoryNotFoundException(categoryName));

                String username = getAuthentication().getName();
                Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new UserNotFoundException(ROLE, username));

                admin.getQuizzes().forEach(quiz -> {
                        if (Objects.equals(quiz.getVersion(), version.toUpperCase())
                                        && Objects.equals(quiz.getCategory().getCategoryName(),
                                                        capitalizeFirstLetter(categoryName))) {
                                throw new QuizAlreadyExistsException(categoryName, version.toUpperCase(), quiz.getId());
                        }
                });

                List<Question> listOfQuestionsForQuizCreation = new ArrayList<>();

                List<Integer> listOfInsertedIds = parseStringOfIdsToListOfIds(stringOfIds);
                listOfInsertedIds.forEach(insertedId -> {
                        if (insertedId != null) {
                                Question questionEntity = questionRepository.findById(insertedId)
                                                .orElseThrow(
                                                                () -> new QuestionNotFoundException(insertedId));

                                if (!categoryEntity.getQuestions().contains(questionEntity)) {
                                        throw new QuestionNotFoundException(categoryName, insertedId);
                                }

                                listOfQuestionsForQuizCreation.add(questionEntity);
                        }
                });

                Quiz quiz = new Quiz();
                quiz.setCategory(categoryEntity);
                quiz.setVersion(version.toUpperCase());
                quiz.setNoOfQuestions(listOfInsertedIds.size());
                quiz.setQuestions(listOfQuestionsForQuizCreation);
                quiz.setAdmin(admin);
                quizRepository.save(quiz);

                // Retrieve the quiz to verify successful storage and availability for retrieval
                Quiz quizEntity = quizRepository.findByCategoryAndVersion(categoryEntity, version.toUpperCase())
                                .orElseThrow(() -> new QuizNotFoundException(categoryName, version.toUpperCase()));
                return mapper.map(quizEntity, QuizAdminDTO.class);
        }

        @Transactional
        public QuizAdminDTO updateVersionOfQuiz(Integer id, String version) {

                String username = getAuthentication().getName();
                Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new UserNotFoundException(ROLE, username));

                Quiz quizEntity = quizRepository.findByIdAndAdmin(id, admin)
                                .orElseThrow(() -> new QuizNotFoundException(id));

                Category categoryEntity = categoryRepository
                                .findByCategoryName(quizEntity.getCategory().getCategoryName())
                                .get();

                admin.getQuizzes().forEach(quiz -> {
                        if (Objects.equals(quiz.getVersion(), version.toUpperCase())
                                        && Objects.equals(quiz.getCategory().getCategoryName(),
                                                        quizEntity.getCategory().getCategoryName())) {
                                throw new QuizAlreadyExistsException(categoryEntity.getCategoryName(),
                                                version.toUpperCase(),
                                                quiz.getId());
                        }
                });
                quizEntity.setVersion(version.toUpperCase());
                return mapper.map(quizEntity, QuizAdminDTO.class);
        }

        @Transactional
        public QuizAdminDTO addNumberOfRandomQuestionsToQuiz(Integer id, @NonNull Integer noOfQuestions) {

                if (noOfQuestions <= 0) {
                        throw new IllegalStateException("Invalid number of questions");
                }

                String username = getAuthentication().getName();
                Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new UserNotFoundException(ROLE, username));

                Quiz quizEntity = quizRepository.findByIdAndAdmin(id, admin)
                                .orElseThrow(() -> new QuizNotFoundException(id));

                Category categoryEntity = categoryRepository
                                .findByCategoryName(quizEntity.getCategory().getCategoryName())
                                .get();

                List<Question> listOfQuestionsInCategoryEntityAndNotInQuizEntity = categoryEntity.getQuestions()
                                .stream()
                                .filter(question -> !quizEntity.getQuestions().contains(question))
                                .collect(Collectors.toList());

                if (listOfQuestionsInCategoryEntityAndNotInQuizEntity.size() < noOfQuestions) {
                        throw new CategoryHasInsufficientQuestionsException(categoryEntity.getCategoryName(),
                                        noOfQuestions,
                                        listOfQuestionsInCategoryEntityAndNotInQuizEntity.size());
                }

                quizEntity.getQuestions()
                                .addAll(listOfQuestionsInCategoryEntityAndNotInQuizEntity.subList(0,
                                                noOfQuestions));
                quizEntity.setNoOfQuestions(quizEntity.getQuestions().size());

                return mapper.map(quizEntity, QuizAdminDTO.class);
        }

        @Transactional
        public QuizAdminDTO addNumberOfSelectedQuestionsToQuiz(Integer id, String ids) {

                String username = getAuthentication().getName();
                Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new UserNotFoundException(ROLE, username));

                Quiz quizEntity = quizRepository.findByIdAndAdmin(id, admin)
                                .orElseThrow(() -> new QuizNotFoundException(id));

                if (ids != null && ids.length() > 0) {

                        List<Integer> listOfIds = parseStringOfIdsToListOfIds(ids);

                        listOfIds.forEach(insertedId -> {
                                if (insertedId != null) {
                                        questionRepository.findById(insertedId).orElseThrow(
                                                        () -> new QuestionNotFoundException(insertedId));
                                }
                        });

                        quizEntity.getQuestions().forEach(
                                        question -> {
                                                if (listOfIds.contains(question.getId())) {
                                                        throw new QuestionAlreadyExistsException(question.getId(),
                                                                        quizEntity.getId());
                                                }
                                        });

                        List<Question> questionsToBeAdded = listOfIds.stream()
                                        .map(insertedId ->  questionRepository.findById(insertedId).get())
                                        .collect(Collectors.toList());

                        quizEntity.getQuestions().addAll(questionsToBeAdded);
                        quizEntity.setNoOfQuestions(quizEntity.getQuestions().size());
                }
                return mapper.map(quizEntity, QuizAdminDTO.class);
        }

        @Transactional
        public QuizAdminDTO removeNumberOfRandomQuestionsFromQuiz(Integer id, @NonNull Integer noOfQuestions) {

                if (noOfQuestions <= 0) {
                        throw new IllegalStateException("Invalid number of questions");
                }
                String username = getAuthentication().getName();
                Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new UserNotFoundException(ROLE, username));

                Quiz quizEntity = quizRepository.findByIdAndAdmin(id, admin)
                                .orElseThrow(() -> new QuizNotFoundException(id));

                quizEntity.getQuestions().subList(0, noOfQuestions).clear();
                quizEntity.setNoOfQuestions(quizEntity.getQuestions().size());

                return mapper.map(quizEntity, QuizAdminDTO.class);
        }

        @Transactional
        public QuizAdminDTO removeSelectedQuestionsFromQuiz(Integer id, String stringOfIds) {

                if (isNullOrEmpty(stringOfIds)) {
                        throw new IdsNotSelectedException();
                }

                String username = getAuthentication().getName();
                Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new UserNotFoundException(ROLE, username));

                Quiz quizEntity = quizRepository.findByIdAndAdmin(id, admin)
                                .orElseThrow(() -> new QuizNotFoundException(id));

                List<Integer> listOfIds = parseStringOfIdsToListOfIds(stringOfIds);

                listOfIds.forEach(insertedId -> {
                        if (insertedId != null) {
                                questionRepository.findById(insertedId).orElseThrow(
                                                () -> new QuestionNotFoundException(insertedId));
                        }
                });

                listOfIds.forEach(insertedId -> {
                        if (!quizEntity.getQuestions().stream().map(Question::getId).toList().contains(insertedId)) {
                                throw new QuestionNotFoundException(insertedId, quizEntity.getId());
                        }
                });

                listOfIds.forEach(insertedId -> {
                        if (insertedId != null) {
                                Question question = questionRepository.findById(insertedId).get();
                                quizEntity.getQuestions().remove(question);
                        }
                });

                quizEntity.setNoOfQuestions(quizEntity.getQuestions().size());

                QuizAdminDTO quizAdminDTO = mapper.map(quizEntity, QuizAdminDTO.class);
                return quizAdminDTO;
        }

        @Transactional
        public QuizAdminDTO removeAllQuestionsFromQuizById(@NonNull Integer id) {
                Quiz quizEntity = quizRepository.findById(id)
                                .orElseThrow(() -> new QuizNotFoundException(id));
                quizEntity.getQuestions().removeAll(quizEntity.getQuestions());
                quizEntity.setNoOfQuestions(quizEntity.getQuestions().size());
                return mapper.map(quizEntity, QuizAdminDTO.class);
        }

        public QuizAdminDTO removeQuizById(@NonNull Integer id) {
                String username = getAuthentication().getName();
                Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new UserNotFoundException(ROLE, username));

                Quiz quizEntity = quizRepository.findByIdAndAdmin(id, admin)
                                .orElseThrow(() -> new QuizNotFoundException(id));
                QuizAdminDTO quizAdminDTO = mapper.map(quizEntity, QuizAdminDTO.class);
                quizRepository.deleteById(id);
                return quizAdminDTO;
        }

        private List<Integer> parseStringOfIdsToListOfIds(String ids) {
                try {
                        return Arrays.stream(ids.split(","))
                                        .map(String::trim)
                                        .map(Integer::valueOf)
                                        .collect(Collectors.toList());
                } catch (NumberFormatException e) {
                        String invalidInput = e.getMessage().replaceAll("For input string: ", "");
                        throw new InvalidIdsException(invalidInput);
                }
        }

        private boolean isNullOrEmpty(String str) {
                return str == null || str.trim().isEmpty();
        }

        private String capitalizeFirstLetter(String input) {
                if (input == null || input.isEmpty()) {
                        return input;
                }
                return input.substring(0, 1).toUpperCase() + input.substring(1);
        }

        private Authentication getAuthentication() {
                return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                                .orElseThrow(() -> new IllegalStateException("Authentication Not Found."));
        }

}