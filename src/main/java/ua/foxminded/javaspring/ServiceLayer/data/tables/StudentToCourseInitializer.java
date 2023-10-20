package ua.foxminded.javaspring.ServiceLayer.data.tables;

import ua.foxminded.javaspring.ServiceLayer.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

import java.util.List;

public class StudentToCourseInitializer {

    private StudentAtCourseDAO studentAtCourseDAO;

    private DataConduct dataConduct;

    private ReadResourcesFile readResourcesFile;

    private SQLQueryIsTableExist isTableExist;

    private SQLQueryOfCreateTable createTable;

    private List<StudentAtCourse> studentAtCourses;

    public StudentToCourseInitializer(StudentAtCourseDAO studentAtCourseDAO, DataConduct dataConduct, ReadResourcesFile readResourcesFile, SQLQueryIsTableExist isTableExist, SQLQueryOfCreateTable createTable) {
        this.studentAtCourseDAO = studentAtCourseDAO;
        this.dataConduct = dataConduct;
        this.readResourcesFile = readResourcesFile;
        this.isTableExist = isTableExist;
        this.createTable = createTable;
    }

    public void initializeStudentToCourseTableAndData() {
        if (studentAtCourseDAO.isStudentToCourseTableExist(isTableExist.getStudentToCourseTableExist())) {
            insertIfTableIsEmpty();
        } else {
            studentAtCourseDAO.createStudentToCourseTable(readResourcesFile.getScript(createTable.getStudentToCourseFilePath()));
            insertStudentToCourseIntoTable();
        }
    }

    private void insertIfTableIsEmpty() {
        if (studentAtCourseDAO.isStudentToCourseTableEmpty()) {
            insertStudentToCourseIntoTable();
        }
    }

    private void insertStudentToCourseIntoTable() {
        generateStudentAtCoursesData();
        studentAtCourses.forEach(t -> studentAtCourseDAO.addStudentToCourse(t.getStudent(), t.getCourse()));
    }

    private void generateStudentAtCoursesData() {
        studentAtCourses = dataConduct.createRelationStudentCourse();
    }
}
