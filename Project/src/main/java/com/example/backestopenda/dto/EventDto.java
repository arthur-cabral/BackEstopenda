package com.example.backestopenda.dto;

import com.example.backestopenda.models.Student;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class EventDto {
    private Long eventId;
    private String eventName;
    private Date eventDate;
    private String eventType;
    private Date dateCreated;
    private Student student;

    @JsonProperty("student")
    private void settingValueOfStudent(Long studentId){
        this.student = new Student();
        student.setStudentId(studentId);
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
