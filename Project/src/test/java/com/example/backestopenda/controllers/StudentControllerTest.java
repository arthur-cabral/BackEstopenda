package com.example.backestopenda.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.backestopenda.util.MockUtil;
import com.example.backestopenda.dto.StudentDto;
import com.example.backestopenda.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest
class StudentControllerTest {

    @Autowired
    StudentController studentController;

    StudentService studentService;

    @BeforeEach
    void initialize(){
        studentService = mock(StudentService.class);
        studentController = new StudentController(studentService);
    }

    @Test
    void findAllShouldReturnAllStudents() {
        Pageable pageable = PageRequest.of(1, 10);
        ResponseEntity<Page<StudentDto>> result = studentController.findAll(pageable);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void findByIdShouldReturnStudentIfExistent() {
        StudentDto mockStudent = MockUtil.createMockStudentDto();

        when(studentService.findById(any(Long.class))).thenReturn(mockStudent);

        ResponseEntity<StudentDto> result = studentController.findById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void findByIdShouldReturnNotFoundIfNotExistent() {
        when(studentService.findById(any(Long.class))).thenReturn(null);

        ResponseEntity<StudentDto> result = studentController.findById(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void createShouldReturnStudent() {
        StudentDto studentDto = MockUtil.createMockStudentDto();

        assertNotNull(studentDto);

        when(studentService.create(any(StudentDto.class))).thenReturn(studentDto);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost");

        ResponseEntity<StudentDto> result = studentController.create(studentDto, uriBuilder);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    void updateShouldReturnStudent() {
        StudentDto studentDto = MockUtil.createMockStudentDto();

        assertNotNull(studentDto);

        when(studentService.update(any(Long.class), any(StudentDto.class))).thenReturn(studentDto);

        ResponseEntity<StudentDto> result = studentController.update(1L, studentDto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    void updateShouldReturnNotFound() {
        StudentDto studentDto = MockUtil.createMockStudentDto();

        assertNotNull(studentDto);

        when(studentService.update(any(Long.class), any(StudentDto.class))).thenReturn(null);

        ResponseEntity<StudentDto> result = studentController.update(1L, studentDto);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void deleteShouldReturnNoContent(){
        when(studentService.delete(any(Long.class))).thenReturn(true);

        ResponseEntity<?> result = studentController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void deleteShouldReturnNotFound(){
        when(studentService.delete(any(Long.class))).thenReturn(false);

        ResponseEntity<?> result = studentController.delete(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}