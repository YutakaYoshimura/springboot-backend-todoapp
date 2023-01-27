package com.example.todoapp.entity;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoRequest {

    @NotNull
    private String title;

    @NotNull
    private LocalDateTime date;
    
    @NotNull
    private int category;

    @NotNull
    private String memo;
}
