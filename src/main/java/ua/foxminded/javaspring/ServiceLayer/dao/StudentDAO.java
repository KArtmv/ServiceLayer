package ua.foxminded.javaspring.ServiceLayer.dao;

import java.util.List;
import java.util.Optional;

import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

public interface StudentDAO {

    Optional<Student> getStudentByID(Student student);

    boolean deleteStudent(Student student);

    List<StudentAtCourse> studentCourses(Student student);

    boolean addStudent(Student student);

    boolean isValidStudentID(Student student);

    boolean isTableExist(String sqlQuery);

    void createStudentTable(String sqlQuery);

    boolean isTableEmpty();
}
