package com.project.todoapp.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    Integer id;

    @NotBlank
    @Length(min = 5, max=100, message="Name is not valid")
    String name;
    
    @Pattern(regexp="CREATED|IN_PROGRESS|COMPLETED", message="Invalid status")
    String status;

    public Todo() {
        this.status = TodoStatus.CREATED.name();
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
