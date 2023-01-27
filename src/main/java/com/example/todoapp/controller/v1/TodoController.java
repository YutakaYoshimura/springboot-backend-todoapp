package com.example.todoapp.controller.v1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import com.example.todoapp.entity.TodoRequest;
import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private TodoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Long id) {
        Todo todo = todoService.getTodo(id);
        if (todo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<Object> getAll() {
        // List<Todo> todoList;
        // todoList = category == 0 ? todoService.getAllTodo() : todoService.getTodoByCategory(category);
        List<Todo> todoList = todoService.getAllTodo();
        if (todoList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
        try {
            Todo todo = todoService.updateTodo(id, todoRequest);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } catch (NotFoundException ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Todo todo = todoService.getTodo(id);
        if (todo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todo =  todoService.deleteTodo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
}
