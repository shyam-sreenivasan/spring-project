package com.project.todoapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.todoapp.model.Todo;
import com.project.todoapp.repository.TodoRepository;


@RestController
@RequestMapping("/todos")
public class TodoController {
    TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("")
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Void> saveTodo(@RequestBody Todo entity) {
        Todo todo = new Todo();
        todo.setName(entity.getName());
        todoRepository.save(todo);
        return ResponseEntity.created(null).build();
    }
    

}