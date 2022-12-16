package com.example.todoapp.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "todos")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "category", nullable = false)
    private int category;

    @Column(name = "memo", nullable = false)
    private String memo;

    @Column(name = "delflg", nullable = false)
    private boolean delflg;
}