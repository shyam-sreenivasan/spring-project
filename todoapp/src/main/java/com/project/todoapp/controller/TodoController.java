package com.project.todoapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.todoapp.model.Todo;
import com.project.todoapp.model.TodoStatus;
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable Integer id, @RequestBody Todo todoToUpdate) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            if (!todoToUpdate.getName().isEmpty()) {
                todo.setName(todoToUpdate.getName());
            }
            TodoStatus statusToChange = todoToUpdate.getStatus();
            for (TodoStatus status : TodoStatus.values()) {
                if (status.equals(statusToChange)) {
                    todo.setStatus(status);
                }
            }                
            todoRepository.save(todo);
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
                Todo todo = optionalTodo.get();
                todoRepository.delete(todo);
                return ResponseEntity.ok().build();
            }
        return ResponseEntity.notFound().build();
    }
}