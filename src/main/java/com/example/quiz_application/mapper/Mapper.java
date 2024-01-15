package com.example.quiz_application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.quiz_application.dto.admin.ModelAnswerDTO;
import com.example.quiz_application.dto.admin.QuizAdminDTO;
import com.example.quiz_application.dto.admin.QuizScoreAdminDTO;
import com.example.quiz_application.dto.student.AnswerSubmissionDTO;
import com.example.quiz_application.dto.student.QuizStudentDTO;
import com.example.quiz_application.dto.student.QuizScoreStudentDTO;
import com.example.quiz_application.model.Question;
import com.example.quiz_application.model.Quiz;
import com.example.quiz_application.model.QuizScore;

@Component
public class Mapper {

        private ModelMapper modelMapper;

        public Mapper() {
                this.modelMapper = new ModelMapper();
                configureMappings();
        }

        private void configureMappings() {

                // ======================== quiz mapping ==========================

                modelMapper.createTypeMap(Quiz.class, QuizAdminDTO.class)
                                .addMapping(src -> src.getAdmin().getAdminName(), QuizAdminDTO::setAuthor);

                modelMapper.createTypeMap(Quiz.class, QuizStudentDTO.class)
                                .addMapping(src -> src.getAdmin().getAdminName(), QuizStudentDTO::setAuthor);

                // ======================== quiz score mapping ==========================

                modelMapper.createTypeMap(QuizScore.class, QuizScoreStudentDTO.class)
                                .addMapping(src -> src.getQuiz().getId(), QuizScoreStudentDTO::setQuizId)
                                .addMapping(src -> src.getQuiz().getCategory().getCategoryName(),
                                                QuizScoreStudentDTO::setCategoryName)
                                .addMapping(src -> src.getQuiz().getVersion(), QuizScoreStudentDTO::setVersion)
                                .addMapping(src -> src.getQuiz().getNoOfQuestions(),
                                                QuizScoreStudentDTO::setOverallScore);

                modelMapper.createTypeMap(QuizScore.class, QuizScoreAdminDTO.class)
                                .addMapping(src -> src.getStudent().getStudentName(), QuizScoreAdminDTO::setStudent)
                                .addMapping(src -> src.getStudent().getUsername(), QuizScoreAdminDTO::setUsername)
                                .addMapping(src -> src.getQuiz().getNoOfQuestions(),
                                                QuizScoreAdminDTO::setOverallScore);

                // ======================== question-answer mapping ==========================

                modelMapper.createTypeMap(Question.class, AnswerSubmissionDTO.class)
                                .addMapping(src -> src.getId(), AnswerSubmissionDTO::setQuestionId)
                                .addMapping(src -> src.getAnswerNumber(), AnswerSubmissionDTO::setSelectedChoiceNumber);

                modelMapper.createTypeMap(Question.class, ModelAnswerDTO.class)
                                .addMapping(src -> src.getId(), ModelAnswerDTO::setQuestionId)
                                .addMapping(src -> src.getAnswerNumber(), ModelAnswerDTO::setRightAnswer);

        }

        public <Source, Destination> Destination map(Source source, Class<Destination> destinationType) {
                return modelMapper.map(source, destinationType);
        }

        public <Source, Destination> List<Destination> mapList(List<Source> sourceList,
                        Class<Destination> destinationType) {
                return sourceList.stream()
                                .map(source -> map(source, destinationType))
                                .collect(Collectors.toList());
        }
}
