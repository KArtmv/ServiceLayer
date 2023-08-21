package ua.foxminded.javaspring.ServiceLayer.data;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;
import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Group;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

@Component
public class InsertDataIntoDatabase {

	private StudentDAO studentDAO;
	private CourseDAO courseDAO;
	private GroupDAO groupDAO;

	public InsertDataIntoDatabase(StudentDAO studentDAO, CourseDAO courseDAO, GroupDAO groupDAO) {
		this.studentDAO = studentDAO;
		this.courseDAO = courseDAO;
		this.groupDAO = groupDAO;
	}

	public void insertStudent(List<Student> students) {
		students.forEach(studentDAO::addStudent);
	}

	public void insertCourse(List<Course> courses) {
		courses.forEach(courseDAO::addCourse);
	}

	public void insertGroup(List<Group> groups) {
		groups.forEach(groupDAO::addGroup);
	}

	public void insertStudentToCourse(List<StudentAtCourse> studentAtCourses) {
		studentAtCourses.forEach(t -> courseDAO.addStodentToCourse(t.getStudent(), t.getCourse()));
	}
}