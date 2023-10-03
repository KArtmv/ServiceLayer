package ua.foxminded.javaspring.ServiceLayer.data;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.tables.CourseInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.GroupInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.StudentInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.StudentToCourseInitializer;

@Component
public class GeneratorDatabaseData {

    private StudentInitializer studentInitializer;
    private CourseInitializer courseInitializer;
    private GroupInitializer groupInitializer;
    private StudentToCourseInitializer studentToCourseInitializer;

    public GeneratorDatabaseData(StudentInitializer studentInitializer, CourseInitializer courseInitializer,
                                 GroupInitializer groupInitializer, StudentToCourseInitializer studentToCourseInitializer) {
        this.studentInitializer = studentInitializer;
        this.courseInitializer = courseInitializer;
        this.groupInitializer = groupInitializer;
        this.studentToCourseInitializer = studentToCourseInitializer;
    }

    @PostConstruct
    public void generateDataAndInsertIntoDatabase() {
        groupInitializer.initializeGroupTablesAndData();

        courseInitializer.initializeCourseTableAndData();

        studentInitializer.initializeStudentTableAndData();

        studentToCourseInitializer.initializeStudentToCourseTableAndData();
    }
}
