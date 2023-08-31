package ua.foxminded.javaspring.ServiceLayer.data.tables;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.model.Course;

@Component
public class CourseTableInitializer {

	private CourseDAO courseDAO;

	public CourseTableInitializer(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public void initializeCourseTableAndData(List<Course> courses) {
		if (courseDAO.isCourseTableExist()) {
			insertIfTableIsEmpty(courses);
		} else {
			courseDAO.createCourseTable();
			insertCoursesIntoTable(courses);
		}
	}

	private void insertIfTableIsEmpty(List<Course> courses) {
		if (courseDAO.isCourseTableEmpty()) {
			insertCoursesIntoTable(courses);
		}
	}

	private void insertCoursesIntoTable(List<Course> courses) {
		courses.forEach(courseDAO::addCourse);
	}
}
