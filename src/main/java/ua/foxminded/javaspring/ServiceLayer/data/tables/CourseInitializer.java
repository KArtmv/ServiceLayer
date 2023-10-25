package ua.foxminded.javaspring.ServiceLayer.data.tables;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.model.Course;

import java.util.List;

public class CourseInitializer {

    private CourseDAO courseDAO;

    private DataConduct dataConduct;

    private ReadResourcesFile readResourcesFile;

    private SQLQueryIsTableExist isTableExist;

    private SQLQueryOfCreateTable createTable;

    private List<Course> courses;

    public CourseInitializer(CourseDAO courseDAO, DataConduct dataConduct, ReadResourcesFile readResourcesFile,
                             SQLQueryIsTableExist isTableExist, SQLQueryOfCreateTable createTable) {
        this.courseDAO = courseDAO;
        this.dataConduct = dataConduct;
        this.readResourcesFile = readResourcesFile;
        this.isTableExist = isTableExist;
        this.createTable = createTable;
    }

    public void initializeCourseTableAndData() {
        if (courseDAO.isCourseTableExist(isTableExist.getCourseTableExist())) {
            insertIfTableIsEmpty();
        } else {
            courseDAO.createCourseTable(readResourcesFile.getScript(createTable.getCourseFilePath()));
            insertCoursesIntoTable();
        }
    }

    private void insertIfTableIsEmpty() {
        if (courseDAO.isCourseTableEmpty()) {
            insertCoursesIntoTable();
        }
    }

    private void insertCoursesIntoTable() {
        generateCoursesData();
        courses.forEach(courseDAO::addCourse);
    }

    private void generateCoursesData() {
        courses = dataConduct.createCourses();
    }
}
