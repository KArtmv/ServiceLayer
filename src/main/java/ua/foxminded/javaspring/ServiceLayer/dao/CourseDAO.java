package ua.foxminded.javaspring.ServiceLayer.dao;

import ua.foxminded.javaspring.ServiceLayer.model.Course;

public interface CourseDAO {
	boolean addCourse(Course course);

	boolean isValidCourseID(Course course);

	boolean isCourseTableExist();

	void createCourseTable();

	boolean isCourseTableEmpty();
}
