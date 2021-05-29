package com.example.crudEnpointsExercise.controller;

import com.example.crudEnpointsExercise.domain.Lessons;
import com.example.crudEnpointsExercise.repository.LessonsRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LessonsController {
    private final LessonsRepository lessonsRepository;

    public  LessonsController(LessonsRepository lessonsRepository){
        this.lessonsRepository=lessonsRepository;
    }

    @GetMapping("/lessons/{id}")
    public Optional<Lessons> getById(@PathVariable int id){
        return this.lessonsRepository.findById(id);
    }

    @DeleteMapping("/lessons/{id}")
    public void deleteById(@PathVariable int id){
        this.lessonsRepository.deleteById(id);
    }

}
