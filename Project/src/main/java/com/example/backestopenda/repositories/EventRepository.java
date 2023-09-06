package com.example.backestopenda.repositories;

import com.example.backestopenda.models.Event;
import com.example.backestopenda.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findAllByStudent(Student id, Pageable pageable);
}
