package ua.foxminded.javaspring.ServiceLayer.data.tables;

import java.util.List;

import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;
import ua.foxminded.javaspring.ServiceLayer.model.Student;

@Component
public class StudentTableInitializer {

	private StudentDAO studentDAO;

	public StudentTableInitializer(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public void initializeStudentTableAndData(List<Student> students) {
		if (studentDAO.isTableExist()) {
			insertIfTatleIsEmpty(students);
		} else {
			studentDAO.createStudentTable();
			insertStudentsIntoTable(students);
		}
	}

	private void insertIfTatleIsEmpty(List<Student> students) {
		if (studentDAO.isTableEmpty()) {
			insertStudentsIntoTable(students);
		}
	}

	private void insertStudentsIntoTable(List<Student> students) {
		students.forEach(studentDAO::addStudent);
	}
}
