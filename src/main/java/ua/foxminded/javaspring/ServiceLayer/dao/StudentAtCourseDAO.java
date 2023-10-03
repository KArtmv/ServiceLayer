package ua.foxminded.javaspring.ServiceLayer.dao;

import java.util.List;

import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

public interface StudentAtCourseDAO {
    List<StudentAtCourse> courseStudents(Course course);

    boolean addStudentToCourse(Student student, Course course);

    boolean removeStudentFromCourse(StudentAtCourse studentAtCourse);

    boolean removeStudentFromAllTheirCourses(Student student);

    boolean isStudentToCourseTableExist();

    void createStudentToCourseTable();

    boolean isStudentToCourseTableEmpty();
}
