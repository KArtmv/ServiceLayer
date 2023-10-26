package ua.foxminded.javaspring.ServiceLayer.data;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.ServiceLayer.data.tables.CourseInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.GroupInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.StudentInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.StudentToCourseInitializer;

@Component
public class GeneratorDatabaseData {
    private GroupInitializer groupInitializer;

    private CourseInitializer courseInitializer;

    private StudentInitializer studentInitializer;

    private StudentToCourseInitializer studentToCourseInitializer;

    @Autowired
    public GeneratorDatabaseData(GroupInitializer groupInitializer, CourseInitializer courseInitializer,
              StudentInitializer studentInitializer, StudentToCourseInitializer studentToCourseInitializer) {
        this.groupInitializer = groupInitializer;
        this.courseInitializer = courseInitializer;
        this.studentInitializer = studentInitializer;
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
