package ua.foxminded.javaspring.ServiceLayer.data.tables;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

@Component
public class StudentToCourseInitializer {

    private StudentAtCourseDAO studentAtCourseDAO;
    private DataConduct dataConduct;

    private List<StudentAtCourse> studentAtCourses;

    public StudentToCourseInitializer(StudentAtCourseDAO studentAtCourseDAO, DataConduct dataConduct) {
        this.studentAtCourseDAO = studentAtCourseDAO;
        this.dataConduct = dataConduct;
    }

    public void initializeStudentToCourseTableAndData() {
        if (studentAtCourseDAO.isStudentToCourseTableExist()) {
            insertIfTableIsEmpty();
        } else {
            studentAtCourseDAO.createStudentToCourseTable();
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
