package com.example.todoapp.service;

import java.util.List;

import com.example.todoapp.entity.TodoRequest;
import com.example.todoapp.model.Todo;

public interface TodoService {
    public Todo getTodo(Long id);

    public List<Todo> getAllTodo();

    public List<Todo> getTodoByCategory(int category);

    public List<Todo> getTodoByDateBetween(String strFrom, String strTo);

    public Todo getTodoMaxId();

    public Todo createTodo(TodoRequest TodoRequest);

    public Todo updateTodo(Long id, TodoRequest TodoRequest);

    public Todo deleteTodo(Long id);
}
