package ua.foxminded.javaspring.ServiceLayer.config;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Random random() {
        return new Random();
    }
}