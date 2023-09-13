package com.example.backestopenda.util;

import com.example.backestopenda.dto.StudentDto;

import java.util.Date;

public class MockUtil {
    public static StudentDto createMockStudentDto() {
        StudentDto studentDto = new StudentDto();
        Date date = new Date();
        studentDto.setName("John Doe");
        studentDto.setCpf("51363563831");
        studentDto.setRa("02201005");
        studentDto.setDateCreated(date);

        return studentDto;
    }
}
