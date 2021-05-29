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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LessonsTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    LessonsRepository lessonsRepository;

    @Test
    @Transactional
    @Rollback
    public void getByIdTest() throws Exception{
        Lessons lessons =new Lessons();
        lessons.setTitle("Java spring");
        this.lessonsRepository.save(lessons);

        MockHttpServletRequestBuilder request=get("/lessons/1")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",is ("Java spring")))
                .andExpect(jsonPath("$.id",is (1)));

    }
    @Test
    @Transactional
    @Rollback
    public void deleteByIdTest() throws Exception{
        Lessons lessons =new Lessons();
        lessons.setTitle("Java spring");
        this.lessonsRepository.save(lessons);

        MockHttpServletRequestBuilder request=delete("/lessons/1")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk());


    }
    @Test
    @Transactional
    @Rollback
    public void postNewLessonsTest() throws Exception{

        MockHttpServletRequestBuilder request=post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Java spring\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.title",is ("Java spring")))
             //   .andExpect(jsonPath("$.id",instanceOf(Number.class)));

    }
}
