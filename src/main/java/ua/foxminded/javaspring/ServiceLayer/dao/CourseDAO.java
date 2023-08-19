package ua.foxminded.javaspring.ServiceLayer.dao;

import java.util.List;

import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

public interface CourseDAO {
	boolean addCourse(Course course);

	List<StudentAtCourse> courseStudents(Course course);

	boolean addStodentToCourse(Student student, Course course);

	boolean removeStudentFromCourse(StudentAtCourse studentAtCourse);

	boolean removeStudentFromAllTheirCourses(Student student);

	boolean isValidCourseID(Course course);

	boolean isCourseTableExist();

	void createCourseTable(String sqlScript);

	boolean isStudentToCourseTableExist();

	void createStodentToCourseTable(String sqlScript);
}
