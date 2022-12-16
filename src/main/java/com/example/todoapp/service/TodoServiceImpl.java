package com.example.todoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoapp.entity.TodoRequest;
import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;

import jakarta.transaction.Transactional;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    private TodoRepository todoRepository;
    
    @Override
    public Todo getTodo(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAllOrderByDate();
    }

    @Override
    public List<Todo> getTodoByCategory(int category) {
        return todoRepository.findByCategoryOrderByDate(category);
    }

    @Override
    public List<Todo> getTodoByDateBetween(String strFrom, String strTo) {
        return todoRepository.findByDateBetween(strFrom, strTo);
    }

    @Override
    public Todo getTodoMaxId() {
        Long maxId = todoRepository.findMaxId();
        maxId = maxId == null ? 0 : maxId;
        return todoRepository.findById(maxId).orElse(null);
    }

    @Transactional
    @Override
    public Todo createTodo(TodoRequest todoRequest) {
        Todo todo = new Todo(
            null,
            todoRequest.getTitle(),
            todoRequest.getDate(),
            todoRequest.getCategory(),
            todoRequest.getMemo(),
            false
        );
        return todoRepository.save(todo);
    }

    @Transactional
    @Override
    public Todo updateTodo(Long id, TodoRequest todoRequest) {
        todoRepository.findById(id).orElse(new Todo());
        Todo todo = new Todo(
                id,
                todoRequest.getTitle(),
                todoRequest.getDate(),
                todoRequest.getCategory(),
                todoRequest.getMemo(),
                false
        );
        return todoRepository.save(todo);
    }

    @Transactional
    @Override
    public Todo deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElse(new Todo());
        Todo todoupdate = new Todo(
                id,
                todo.getTitle(),
                todo.getDate(),
                todo.getCategory(),
                todo.getMemo(),
                true
        );
        return todoRepository.save(todoupdate);
    }
}
