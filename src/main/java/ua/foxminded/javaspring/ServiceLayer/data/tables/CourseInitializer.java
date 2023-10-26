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

    private SQLQueryIsTableExist queryIsTableExist;

    private SQLQueryOfCreateTable queryOfCreateTable;

    private List<Course> courses;

    public CourseInitializer(CourseDAO courseDAO, DataConduct dataConduct, ReadResourcesFile readResourcesFile,
                             SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        this.courseDAO = courseDAO;
        this.dataConduct = dataConduct;
        this.readResourcesFile = readResourcesFile;
        this.queryIsTableExist = queryIsTableExist;
        this.queryOfCreateTable = queryOfCreateTable;
    }

    public void initialize() {
        if (courseDAO.isCourseTableExist(queryIsTableExist.queryForCourseTable())) {
            checkIsTableEmptyAndPopulate();
        } else {
            courseDAO.createCourseTable(readResourcesFile.getScript(queryOfCreateTable.getCourseFilePath()));
            populateTable();
        }
    }

    private void checkIsTableEmptyAndPopulate() {
        if (courseDAO.isCourseTableEmpty()) {
            populateTable();
        }
    }

    private void populateTable() {
        generateData();
        courses.forEach(courseDAO::addCourse);
    }

    private void generateData() {
        courses = dataConduct.createCourses();
    }
}
