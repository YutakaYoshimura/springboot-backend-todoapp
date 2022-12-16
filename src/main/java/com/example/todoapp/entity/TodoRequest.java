package com.example.todoapp.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TodoRequest {

    private String title;

    private LocalDateTime date;
    
    private int category;

    private String memo;
}
