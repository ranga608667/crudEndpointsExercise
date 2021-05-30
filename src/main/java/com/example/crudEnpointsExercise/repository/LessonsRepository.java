package com.example.crudEnpointsExercise.repository;

import com.example.crudEnpointsExercise.domain.Lessons;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LessonsRepository extends CrudRepository<Lessons, Integer> {
    Lessons findByTitle(String title);
    //Lessons findByTitle(String title);
    @Query("select l from Lessons l where l.deliveredOn between ?1 and ?2")
    List<Lessons> findBetweenDates(Date deliveredOnStart,Date deliveredOnEnd);
}
