package ua.foxminded.javaspring.ServiceLayer.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.RandomNumber;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.generator.CourseGenerator;
import ua.foxminded.javaspring.ServiceLayer.data.generator.GroupGenerator;
import ua.foxminded.javaspring.ServiceLayer.data.generator.StudentGenerator;
import ua.foxminded.javaspring.ServiceLayer.data.generator.StudentToCourseGenerator;
import ua.foxminded.javaspring.ServiceLayer.data.resources.CountConfig;
import ua.foxminded.javaspring.ServiceLayer.data.resources.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.data.tables.CourseInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.GroupInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.StudentInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.StudentToCourseInitializer;

@Component
public class Config {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private SQLQueryOfCreateTable createTable;

    @Autowired
    private SQLQueryIsTableExist isTableExist;

    @Autowired
    private ResourcesFilesDatabaseData databaseData;

    @Autowired
    private StudentAtCourseDAO studentAtCourseDAO;

    @Autowired
    private CountConfig countConfig;

    private ReadResourcesFile readResourcesFile;

    public ReadResourcesFile readFile() {
        readResourcesFile = new ReadResourcesFile(resourceLoader);
        return readResourcesFile;
    }

    @Bean
    public GroupInitializer groupInitializer() {
        return new GroupInitializer(groupDAO, dataConduct(), readFile(), isTableExist, createTable);
    }

    @Bean
    public CourseInitializer courseInitializer() {
        return new CourseInitializer(courseDAO, dataConduct(), readFile(), isTableExist, createTable);
    }

    @Bean
    public StudentInitializer studentInitializer() {
        return new StudentInitializer(studentDAO, dataConduct(), readFile(), isTableExist, createTable);
    }

    @Bean
    public StudentToCourseInitializer studentToCourseInitializer() {
        return new StudentToCourseInitializer(studentAtCourseDAO, dataConduct(), readFile(), isTableExist, createTable);
    }

    public DataConduct dataConduct() {
        return new DataConduct(studentGenerator(), courseGenerator(), groupGenerator(), studentToCourseGenerator());
    }

    public StudentGenerator studentGenerator() {
        return new StudentGenerator(new RandomNumber(), readFile(), databaseData, countConfig);
    }

    public CourseGenerator courseGenerator() {
        return new CourseGenerator(readFile(), databaseData);
    }

    public GroupGenerator groupGenerator() {
        return new GroupGenerator(readFile(), databaseData);
    }

    public StudentToCourseGenerator studentToCourseGenerator() {
        return new StudentToCourseGenerator(new RandomNumber(), countConfig);
    }

}
