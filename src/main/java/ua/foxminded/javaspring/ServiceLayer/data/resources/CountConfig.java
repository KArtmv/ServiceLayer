package ua.foxminded.javaspring.ServiceLayer.data.resources;

public class CountConfig {

	private static final Integer MAX_COUNT_COURSES_OF_STUDENT = 3;
	private static final Integer MAX_COUNT_OF_STUDENT = 200;

	public Integer getMaxCountCoursesOfStudent() {
		return MAX_COUNT_COURSES_OF_STUDENT;
	}

	public Integer getMaxCountOfStudents() {
		return MAX_COUNT_OF_STUDENT;
	}
}