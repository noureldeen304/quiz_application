package com.example.quiz_application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    @Autowired
    private static ModelMapper modelMapper;

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
