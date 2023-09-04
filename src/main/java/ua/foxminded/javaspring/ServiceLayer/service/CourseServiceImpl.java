package ua.foxminded.javaspring.ServiceLayer.service;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.model.Course;

public class CourseServiceImpl implements CourseService {

	private CourseDAO courseDAO;

	public CourseServiceImpl(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Override
	public boolean isValidCourseID(Course course) {
		return courseDAO.isValidCourseID(course);
	}
}
