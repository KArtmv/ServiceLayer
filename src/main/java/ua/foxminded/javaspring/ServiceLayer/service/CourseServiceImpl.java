package ua.foxminded.javaspring.ServiceLayer.service;

import java.util.List;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

public class CourseServiceImpl implements CourseService{
	
	private CourseDAO courseDAO;

	public CourseServiceImpl(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Override
	public List<StudentAtCourse> courseStudents(Course course) {
		return courseDAO.courseStudents(course);
	}

	@Override
	public boolean addStodentToCourse(Student student, Course course) {
		return courseDAO.addStodentToCourse(student, course);
	}

	@Override
	public boolean removeStudentFromCourse(StudentAtCourse studentAtCourse) {
		return courseDAO.removeStudentFromCourse(studentAtCourse);
	}

	@Override
	public boolean removeStudentFromAllTheirCourses(Student student) {
		return courseDAO.removeStudentFromAllTheirCourses(student);
	}
}
