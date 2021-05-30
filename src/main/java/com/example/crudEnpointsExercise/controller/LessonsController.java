package com.example.crudEnpointsExercise.controller;

import com.example.crudEnpointsExercise.domain.Lessons;
import com.example.crudEnpointsExercise.repository.LessonsRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
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
        lessonsInput.setDeliveredOn(lessons.getDeliveredOn());
         this.lessonsRepository.save(lessonsInput);
         return this.lessonsRepository.findById(id);
    }

    @GetMapping("/lessons/byTitle/{title}")
    public Lessons getByTitle(@PathVariable String title){
      return this.lessonsRepository.findByTitle(title);
    }

    @GetMapping("/lessons/betweenDates/{StartDate}/{EndDate}")
    public List<Lessons> getByTitle(@PathVariable("StartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date StartDate
                            , @PathVariable("EndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date EndDate){
       // System.out.println("Output from Junit " + this.lessonsRepository.findBetweenDates(StartDate, EndDate));

        return this.lessonsRepository.findBetweenDates(StartDate, EndDate);
    }
}
