package ua.foxminded.javaspring.ServiceLayer.data;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Group;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

@Component
public class GeneratorDatabaseData {

	private InsertDataIntoDatabase dataIntoDatabase;
	private TablesConduct checkIsExist;
	private DataConduct dataConduct;

	public GeneratorDatabaseData(InsertDataIntoDatabase dataIntoDatabase, TablesConduct conduct,
			DataConduct dataConduct) {
		this.dataIntoDatabase = dataIntoDatabase;
		this.checkIsExist = conduct;
		this.dataConduct = dataConduct;
	}

	@PostConstruct
	public void generateDataAndInsertIntoDatabase() {
		group(dataConduct.createGroups());
		student(dataConduct.createStudents());
		course(dataConduct.createCourses());
		studentToCourse(dataConduct.createRelationStudentCourse());
	}

	private void student(List<Student> students) {
		if (!checkIsExist.studentTable()) {
			dataIntoDatabase.insertStudent(students);
		}
	}

	private void group(List<Group> groups) {
		if (!checkIsExist.groupTable()) {
			dataIntoDatabase.insertGroup(groups);
		}
	}

	private void course(List<Course> courses) {
		if (!checkIsExist.courseTable()) {
			dataIntoDatabase.insertCourse(courses);
		}
	}

	private void studentToCourse(List<StudentAtCourse> studentAtCourses) {
		if (!checkIsExist.studentToCourseTable()) {
			dataIntoDatabase.insertStudentToCourse(studentAtCourses);
		}
	}
}
