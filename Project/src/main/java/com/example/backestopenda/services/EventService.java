package com.example.backestopenda.services;

import com.example.backestopenda.dto.EventDto;
import com.example.backestopenda.models.Event;
import com.example.backestopenda.models.Student;
import com.example.backestopenda.repositories.EventRepository;
import com.example.backestopenda.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EventService {
    private final Logger logger = Logger.getLogger(EventService.class.getName());

    @Autowired
    EventRepository eventRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;

    public Page<EventDto> findAll(Pageable pageable){
        logger.info("Finding all events");
        return eventRepository
                .findAll(pageable)
                .map(a -> modelMapper.map(a, EventDto.class));
    }

    public EventDto findById(Long id){
        logger.info("Finding event by id");
        Event event = eventRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(event, EventDto.class);
    }

    public Page<EventDto> findAllByStudentId(Long id, Pageable pageable){
        logger.info("Finding all events by user id");
        Student student = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return eventRepository
                .findAllByStudent(student, pageable)
                .map(a -> modelMapper.map(a, EventDto.class));
    }

    public EventDto create(EventDto dto){
        logger.info("Creating an event");
        Student student = studentRepository.findById(dto.getStudent().getStudentId()).orElseThrow(EntityNotFoundException::new);

        Event event = modelMapper.map(dto, Event.class);
        event.setStudent(student);
        eventRepository.save(event);

        return modelMapper.map(event, EventDto.class);
    }

    public EventDto update(Long id, EventDto dto){
        logger.info("Updating event by id");
        Event event = modelMapper.map(dto, Event.class);
        event.setEventId(id);
        event = eventRepository.save(event);

        return modelMapper.map(event, EventDto.class);
    }

    public void delete(Long id){
        logger.info("Deleting event");
        Event event = eventRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        eventRepository.delete(event);
    }
}
