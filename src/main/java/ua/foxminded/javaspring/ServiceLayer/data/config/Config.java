package ua.foxminded.javaspring.ServiceLayer.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.RandomNumber;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.generator.CourseGenerator;
import ua.foxminded.javaspring.ServiceLayer.data.generator.GroupGenerator;
import ua.foxminded.javaspring.ServiceLayer.data.generator.StudentGenerator;
import ua.foxminded.javaspring.ServiceLayer.data.generator.StudentToCourseGenerator;
import ua.foxminded.javaspring.ServiceLayer.data.resources.CountConfig;
import ua.foxminded.javaspring.ServiceLayer.data.resources.ResourcesFilesDatabaseData;

@Component
public class Config {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ResourcesFilesDatabaseData resourcesFiles;
    
    private RandomNumber randomNumber = new RandomNumber();

    private CountConfig countConfig = new CountConfig();

    @Bean
    public ReadResourcesFile readFile(){
        return new ReadResourcesFile(resourceLoader);
    }

    @Bean
    public DataConduct dataConduct(){
        return new DataConduct(studentGenerator(), courseGenerator(), groupGenerator(), studentToCourseGenerator());
    }

    public StudentGenerator studentGenerator(){
        return new StudentGenerator(randomNumber, readFile(), resourcesFiles, countConfig);
    }

    public CourseGenerator courseGenerator(){
        return new CourseGenerator(readFile(), resourcesFiles);
    }

    public GroupGenerator groupGenerator(){
        return new GroupGenerator(readFile(), resourcesFiles);
    }

    public StudentToCourseGenerator studentToCourseGenerator(){
        return new StudentToCourseGenerator(randomNumber, countConfig);
    }
}
