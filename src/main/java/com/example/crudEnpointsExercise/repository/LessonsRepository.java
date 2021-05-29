package com.example.crudEnpointsExercise.repository;

import com.example.crudEnpointsExercise.domain.Lessons;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonsRepository extends CrudRepository<Lessons, Integer> {
    List<Lessons> findByTitle(String title);
}
