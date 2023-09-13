package com.example.backestopenda.services;

import com.example.backestopenda.dto.StudentDto;
import com.example.backestopenda.models.Student;
import com.example.backestopenda.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StudentService {
    private final Logger logger = Logger.getLogger(StudentService.class.getName());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;

    public Page<StudentDto> findAll(Pageable pageable){
        logger.info("Finding all students");
        return studentRepository
                .findAll(pageable)
                .map(s -> modelMapper.map(s, StudentDto.class));
    }

    public StudentDto findById(Long id){
        logger.info("Finding a student by id");
        Optional<Student> student = studentRepository.findById(id);

        return modelMapper.map(student, StudentDto.class);
    }

    public StudentDto create(StudentDto studentDto) {
        logger.info("Creating a student");
        Student student = modelMapper.map(studentDto, Student.class);
        studentRepository.save(student);

        return modelMapper.map(student, StudentDto.class);
    }

    public StudentDto update(Long id, StudentDto studentDto){
        logger.info("Updating a student");
        if (!studentRepository.existsById(id)){
            return null;
        }
        Student student = modelMapper.map(studentDto, Student.class);
        student.setStudentId(id);
        student = studentRepository.save(student);

        return modelMapper.map(student, StudentDto.class);
    }

    public boolean delete(Long id){
        logger.info("Deleting a student");
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()){
            studentRepository.delete(student.get());
            return true;
        }
        return false;
    }

}
