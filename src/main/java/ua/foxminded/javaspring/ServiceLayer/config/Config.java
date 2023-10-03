package ua.foxminded.javaspring.ServiceLayer.config;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private static final Integer MAX_COUNT_COURSES_OF_STUDENT = 3;

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public Integer maxCountCoursesOfStudent() {
        return MAX_COUNT_COURSES_OF_STUDENT;
    }
}