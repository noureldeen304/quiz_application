package com.example.quiz_application.dto.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionAdminDTO {
    private Integer id;
    private String categoryName;
    private String difficultyLevel;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;

    @JsonIgnore
    public List<String> getOptions() {
        return new ArrayList<>(Arrays.asList(option1, option2, option3, option4));
    }

    @JsonIgnore
    public List<String> getOptionsWithoutOption(String option) {
        List<String> list = new ArrayList<>(Arrays.asList(option1, option2, option3, option4));
        list.remove(option);
        return list;
    }

    @JsonIgnore
    public void setValueForOption(Integer optionNumber, String option) {
        switch (optionNumber) {
            case 1:
                setOption1(option);
                break;
            case 2:
                setOption2(option);
                break;
            case 3:
                setOption3(option);
                break;
            default:
                setOption4(option);
        }
    }
}
