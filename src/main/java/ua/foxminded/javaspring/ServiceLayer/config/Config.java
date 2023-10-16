package ua.foxminded.javaspring.ServiceLayer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.foxminded.javaspring.ServiceLayer.data.resources.CountConfig;

@Configuration
public class Config {

    @Bean
    public CountConfig countConfig (){
        return new CountConfig();
    }
}
