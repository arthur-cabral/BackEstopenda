package com.example.backestopenda.controllers;

import com.example.backestopenda.dto.EventDto;
import com.example.backestopenda.services.EventService;
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

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping
    public ResponseEntity<Page<EventDto>> findAll(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(eventService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> findById(@PathVariable Long id){
        EventDto eventDto = eventService.findById(id);
        return ResponseEntity.ok(eventDto);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Page<EventDto>> findAllByStudentId(@PathVariable Long id, @PageableDefault Pageable pageable){
        return ResponseEntity.ok(eventService.findAllByStudentId(id, pageable));
    }

    @PostMapping
    public ResponseEntity<EventDto> create(@RequestBody EventDto dto, UriComponentsBuilder uriBuilder){
        EventDto eventDto = eventService.create(dto);
        URI path = uriBuilder.path("/event/{id}").buildAndExpand(eventDto.getEventId()).toUri();

        return ResponseEntity.created(path).body(eventDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> update(@PathVariable Long id, @RequestBody EventDto dto){
        EventDto eventDto = eventService.update(id, dto);
        return ResponseEntity.ok(eventDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
