package com.example.quiz_application.Converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class Converter {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <DAO, DTO> DTO convertToDTO(DAO entity, Class<DTO> dtoClass) {

        return modelMapper.map(entity, dtoClass);
    }

    public static <DAO, DTO> List<DTO> convertToDTOs(List<DAO> questions, Class<DTO> dtoClass) {

        return questions.stream()
                .map(question -> convertToDTO(question, dtoClass))
                .collect(Collectors.toList());
    }

    public static <DTO, DAO> DAO convertToDAO(DTO question, Class<DAO> daoClass) {

        return modelMapper.map(question, daoClass);
    }

    public static <DTO, DAO> List<DAO> convertToDAOs(List<DTO> questions, Class<DAO> daoClass) {
        return questions.stream()
                .map(question -> convertToDAO(question, daoClass))
                .collect(Collectors.toList());
    }
}
