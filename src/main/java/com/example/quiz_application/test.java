package com.example.quiz_application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
        String option1 = "hello, ";
        String option2 = "world";
        List<String> list = new ArrayList<>(Arrays.asList(option1, option2));
        System.out.println(list.toString());
        list.set(0, "Hi, ");
        System.out.println(list.toString());
        System.out.println("option1: " + option1);
    }
}
