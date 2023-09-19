package com.example.backestopenda.controllers;

import com.example.backestopenda.dto.StudentDto;
import com.example.backestopenda.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/student")
@Tag(name = "Student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @Operation(summary = "Finds all students")
    public ResponseEntity<Page<StudentDto>> findAll(@PageableDefault() Pageable pageable) {

        return ResponseEntity.ok(studentService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds an student by id")
    public ResponseEntity<StudentDto> findById(@PathVariable(value = "id") Long id) {
        StudentDto studentDto = studentService.findById(id);
        if (studentDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Creates an student")
    public ResponseEntity<StudentDto> create(@RequestBody StudentDto dto, UriComponentsBuilder uriBuilder){
        StudentDto studentDto = studentService.create(dto);
        URI path = uriBuilder.path("/student/{id}").buildAndExpand(studentDto.getStudentId()).toUri();

        return ResponseEntity.created(path).body(studentDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates an student")
    public ResponseEntity<StudentDto> update(@PathVariable Long id, @RequestBody StudentDto dto){
        StudentDto studentDto = studentService.update(id, dto);
        if (studentDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes an student")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (studentService.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
