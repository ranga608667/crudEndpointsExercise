package com.example.crudEnpointsExercise.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Date;

@Entity
//@NamedQuery(name="Lessons.findByTitle",
//query="Select l from Lessons l where l.title=?1")

public class Lessons {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String title;
    @Column
    private Date deliveredOn;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getDeliveredOn() {
        return deliveredOn;
    }

    public void setDeliveredOn(Date deliveredOn) {
        this.deliveredOn = deliveredOn;
    }

    public Lessons(){
    }

    public Lessons(String title){
        this.title=title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        return "Lesson [id =" + id + ", title=" + title + " ]";
    }
}
