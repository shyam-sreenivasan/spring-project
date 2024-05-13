package com.project.todoapp.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.project.todoapp.model.Todo;

@Repository
public interface  TodoRepository extends ListCrudRepository<Todo, Integer> {
    
}
