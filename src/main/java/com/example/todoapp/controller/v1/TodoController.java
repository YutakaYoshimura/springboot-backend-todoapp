package com.example.todoapp.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.exception.ErrorMessage;
import com.example.todoapp.entity.TodoRequest;
import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Long id) {
        Todo todo = todoService.getTodo(id);
        if (todo == null) {
            ErrorMessage res = new ErrorMessage();
            res.setErrorMessage("該当データが存在しません。");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
    
    @PostMapping("/all")
    public ResponseEntity<Object> getAll(@RequestBody int category) {
        List<Todo> todoList;
        todoList = category == 0 ? todoService.getAllTodo() : todoService.getTodoByCategory(category);
        if (todoList.size() == 0) {
            ErrorMessage res = new ErrorMessage();
            res.setErrorMessage("該当データが存在しません。");
            return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @GetMapping("/date/from/{strFrom}/to/{strTo}")
    public ResponseEntity<Object> getTodoByDateBetween(@PathVariable String strFrom,
            @PathVariable String strTo) {
        List<Todo> todoList = todoService.getTodoByDateBetween(strFrom, strTo);
        if (todoList.size() == 0) {
            return new ResponseEntity<>(todoList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @GetMapping("/maxid")
    public ResponseEntity<Object> getMaxId() {
        Todo todo = todoService.getTodoMaxId();
        if (todo == null) {
            return new ResponseEntity<>(todo, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
    
    @PostMapping("")
    public Todo add(@RequestBody TodoRequest todoRequest) {
        return todoService.createTodo(todoRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody TodoRequest todoRequest) {
        Todo todo = todoService.getTodo(id);
        if (todo == null) {
            ErrorMessage res = new ErrorMessage();
            res.setErrorMessage("該当データが存在しません。");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        todo =  todoService.updateTodo(id, todoRequest);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Todo todo = todoService.getTodo(id);
        if (todo == null) {
            ErrorMessage res = new ErrorMessage();
            res.setErrorMessage("該当データが存在しません。");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        todo =  todoService.deleteTodo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
}
