package ua.foxminded.javaspring.ServiceLayer.data.tables;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

@Component
public class StudentToCourseInitializer {

	private CourseDAO courseDAO;
	private DataConduct dataConduct;

	private List<StudentAtCourse> studentAtCourses = new ArrayList<>();

	public StudentToCourseInitializer(CourseDAO courseDAO, DataConduct dataConduct) {
		this.courseDAO = courseDAO;
		this.dataConduct = dataConduct;
	}

	public void initializeStudentToCourseTableAndData() {
		if (courseDAO.isStudentToCourseTableExist()) {
			insertIfTableIsEmpty();
		} else {
			courseDAO.createStodentToCourseTable();
			insertStudentToCourseIntoTable();
		}
	}

	private void insertIfTableIsEmpty() {
		if (courseDAO.isStudentToCourseTableEmpty()) {
			insertStudentToCourseIntoTable();
		}
	}

	private void insertStudentToCourseIntoTable() {
		generateStudentAtCoursesData();
		studentAtCourses.forEach(t -> courseDAO.addStodentToCourse(t.getStudent(), t.getCourse()));
	}

	private List<StudentAtCourse> generateStudentAtCoursesData() {
		studentAtCourses = dataConduct.createRelationStudentCourse();
		return studentAtCourses;
	}
}
