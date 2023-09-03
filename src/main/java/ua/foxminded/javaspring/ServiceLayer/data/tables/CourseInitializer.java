package ua.foxminded.javaspring.ServiceLayer.data.tables;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.model.Course;

@Component
public class CourseInitializer {

	private CourseDAO courseDAO;
	private DataConduct dataConduct;

	private List<Course> courses;

	public CourseInitializer(CourseDAO courseDAO, DataConduct dataConduct) {
		this.courseDAO = courseDAO;
		this.dataConduct = dataConduct;
	}

	public void initializeCourseTableAndData() {
		if (courseDAO.isCourseTableExist()) {
			insertIfTableIsEmpty();
		} else {
			courseDAO.createCourseTable();
			insertCoursesIntoTable();
		}
	}

	private void insertIfTableIsEmpty() {
		if (courseDAO.isCourseTableEmpty()) {
			insertCoursesIntoTable();
		}
	}

	private void insertCoursesIntoTable() {
		generateCoursesData();
		courses.forEach(courseDAO::addCourse);
	}

	private List<Course> generateCoursesData() {
		courses = dataConduct.createCourses();
		return courses;
	}
}
