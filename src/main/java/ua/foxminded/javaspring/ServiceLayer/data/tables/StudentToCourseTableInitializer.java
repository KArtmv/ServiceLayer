package ua.foxminded.javaspring.ServiceLayer.data.tables;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

@Component
public class StudentToCourseTableInitializer {

	private CourseDAO courseDAO;

	public StudentToCourseTableInitializer(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public void initializeStudentToCourseTableAndData(List<StudentAtCourse> studentAtCourses) {
		if (courseDAO.isStudentToCourseTableExist()) {
			insertIfTableIsEmpty(studentAtCourses);
		} else {
			courseDAO.createStodentToCourseTable();
			insertStudentToCourseIntoTable(studentAtCourses);
		}
	}

	private void insertIfTableIsEmpty(List<StudentAtCourse> studentAtCourses) {
		if (courseDAO.isStudentToCourseTableEmpty()) {
			insertStudentToCourseIntoTable(studentAtCourses);
		}
	}

	private void insertStudentToCourseIntoTable(List<StudentAtCourse> studentAtCourses) {
		studentAtCourses.forEach(t -> courseDAO.addStodentToCourse(t.getStudent(), t.getCourse()));
	}
}
