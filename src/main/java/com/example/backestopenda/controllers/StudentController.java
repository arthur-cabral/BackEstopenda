package com.example.backestopenda.controllers;

import com.example.backestopenda.dto.StudentDto;
import com.example.backestopenda.models.Student;
import com.example.backestopenda.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<StudentDto>> findAll(@PageableDefault() Pageable pageable) {

        return ResponseEntity.ok(studentService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable(value = "id") Long id) {
        StudentDto studentDto = studentService.findById(id);
        if (studentDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> create(@RequestBody StudentDto dto, UriComponentsBuilder uriBuilder){
        StudentDto studentDto = studentService.create(dto);
        URI path = uriBuilder.path("/students/{id}").buildAndExpand(studentDto.getId()).toUri();

        return ResponseEntity.created(path).body(studentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable Long id, @RequestBody StudentDto dto){
        StudentDto studentDto = studentService.update(id, dto);
        return ResponseEntity.ok(studentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
