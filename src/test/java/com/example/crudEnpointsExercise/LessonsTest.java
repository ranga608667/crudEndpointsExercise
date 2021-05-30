package com.example.crudEnpointsExercise;

import com.example.crudEnpointsExercise.domain.Lessons;
import com.example.crudEnpointsExercise.repository.LessonsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LessonsTest {
    @Autowired
    LessonsRepository lessonsRepository;
    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    @Rollback
    public void getByIdTest() throws Exception {
        Lessons lessons = new Lessons();
        lessons.setTitle("Java spring");
        lessons.setDeliveredOn(new Date());
        this.lessonsRepository.save(lessons);

        MockHttpServletRequestBuilder request = get("/lessons/1")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Java spring")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.deliveredOn", is("2021-05-30")));

    }

    @Test
    @Transactional
    @Rollback
    public void deleteByIdTest() throws Exception {
        Lessons lessons = new Lessons();
        lessons.setTitle("Java spring");
        this.lessonsRepository.save(lessons);

        MockHttpServletRequestBuilder request = delete("/lessons/1")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void postNewLessonsTest() throws Exception {

        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Java spring\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void updateLessonsByID() throws Exception {
        Lessons lessons = new Lessons();
        lessons.setTitle("Java spring");
        this.lessonsRepository.save(lessons);

        MockHttpServletRequestBuilder request = patch("/lessons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Java spring Boot\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Java spring Boot")));
    }

    @Test
    @Transactional
    @Rollback
    public void getByTitle() throws Exception {
        Lessons lessons = new Lessons();
        lessons.setTitle("Java spring");
        lessons.setDeliveredOn(new Date());
        this.lessonsRepository.save(lessons);

        MockHttpServletRequestBuilder request = get("/lessons/byTitle/Java Spring")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk());
    //  .andExpect(jsonPath("$.title", is("Java spring")));


    }

    @Test
    @Transactional
    @Rollback
    public void getByBetweenDates() throws Exception {
        Lessons lessons = new Lessons();
        lessons.setTitle("Java spring");
        lessons.setDeliveredOn(new Date(2021,05,26));
        this.lessonsRepository.save(lessons);

        Lessons lessons1 = new Lessons();
        lessons1.setTitle("Java springBoot");
        lessons1.setDeliveredOn(new Date(2021,05,27));
        this.lessonsRepository.save(lessons1);

        MockHttpServletRequestBuilder request = get("/lessons/betweenDates/2021-05-25/2021-05-29")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk());
            //    .andExpect(jsonPath("$.title", is("Java spring")))
             //   .andExpect(jsonPath("$.deliveredOn", is("2021-05-26")));


    }
}
