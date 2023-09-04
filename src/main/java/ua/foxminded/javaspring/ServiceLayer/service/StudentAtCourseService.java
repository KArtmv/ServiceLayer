package ua.foxminded.javaspring.ServiceLayer.service;

import java.util.List;

import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

public interface StudentAtCourseService {
	List<StudentAtCourse> courseStudents(Course course);

	boolean addStodentToCourse(Student student, Course course);

	boolean removeStudentFromCourse(StudentAtCourse studentAtCourse);

	boolean removeStudentFromAllTheirCourses(Student student);
}
