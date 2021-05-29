package com.example.crudEnpointsExercise.controller;

import com.example.crudEnpointsExercise.domain.Lessons;
import com.example.crudEnpointsExercise.repository.LessonsRepository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/lessons")
    public void newLesson(@RequestBody Lessons lessons){
        this.lessonsRepository.save(lessons);
    }

    @PatchMapping("/lessons/{id}")
    public Optional<Lessons> updateById(@RequestBody Lessons lessons, @PathVariable int id){
        Lessons lessonsInput = this.lessonsRepository.findById(id).get();
        lessonsInput.setTitle(lessons.getTitle());
         this.lessonsRepository.save(lessonsInput);
         return this.lessonsRepository.findById(id);
    }
}
