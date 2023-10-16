package ua.foxminded.javaspring.ServiceLayer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.foxminded.javaspring.ServiceLayer.data.resources.CountConfig;
import ua.foxminded.javaspring.ServiceLayer.data.resources.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLFilesOfCreateTables;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLScriptTablesExist;

@Configuration
public class Config {
    @Bean
    public CountConfig countConfig() {
        return new CountConfig();
    }

    @Bean
    public ResourcesFilesDatabaseData resourcesFilesDatabaseData() {
        return new ResourcesFilesDatabaseData();
    }

    @Bean
    public SQLFilesOfCreateTables filesOfCreateTables() {
        return new SQLFilesOfCreateTables();
    }

    @Bean
    public SQLScriptTablesExist scriptTablesExist() {
        return new SQLScriptTablesExist();
    }
}
