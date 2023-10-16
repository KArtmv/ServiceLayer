package ua.foxminded.javaspring.ServiceLayer.options;

import java.util.List;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.ServiceLayer.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;
import ua.foxminded.javaspring.ServiceLayer.service.StudentAtCourseService;

@Service
public class StudentAtCourseServiceImpl implements StudentAtCourseService {

    private StudentAtCourseDAO studentAtCourseDAO;

    public StudentAtCourseServiceImpl(StudentAtCourseDAO studentAtCourseDAO) {
        this.studentAtCourseDAO = studentAtCourseDAO;
    }

    @Override
    public List<StudentAtCourse> allStudentsFromCourse(Course course) {
        return studentAtCourseDAO.allStudentsFromCourse(course);
    }

    @Override
    public boolean addStudentToCourse(Student student, Course course) {
        return studentAtCourseDAO.addStudentToCourse(student, course);
    }

    @Override
    public boolean removeStudentFromCourse(StudentAtCourse studentAtCourse) {
        return studentAtCourseDAO.removeStudentFromCourse(studentAtCourse);
    }

    @Override
    public boolean removeStudentFromAllTheirCourses(Student student) {
        return studentAtCourseDAO.removeStudentFromAllTheirCourses(student);
    }
}
