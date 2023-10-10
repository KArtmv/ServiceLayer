package ua.foxminded.javaspring.ServiceLayer.data.tables;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.model.Student;

@RunWith(MockitoJUnitRunner.class)
public class StudentInitializerTest {

	@Mock
	private StudentDAO studentDAO;

	@Mock
	private DataConduct dataConduct;

	private StudentInitializer initializer;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		initializer = new StudentInitializer(studentDAO, dataConduct);
	}

	@Test
	void initializeStudentTablesAndData_whenGroupTableExist() {
		Student student = new Student("firsName", "lastName");
		List<Student> students = Arrays.asList(student, student, student);

		when(studentDAO.isTableExist()).thenReturn(true);
		when(studentDAO.isTableEmpty()).thenReturn(true);
		when(dataConduct.createStudents()).thenReturn(students);

		initializer.initializeStudentTableAndData();

		verify(studentDAO).isTableExist();
		verify(studentDAO).isTableEmpty();
		verify(dataConduct).createStudents();
	}

	@Test
	void initializeStudentTablesAndData_whenGroupTableNotExist() {
		Student student = new Student("firsName", "lastName");
		List<Student> students = Arrays.asList(student, student, student);

		when(studentDAO.isTableExist()).thenReturn(false);
		when(dataConduct.createStudents()).thenReturn(students);

		initializer.initializeStudentTableAndData();

		verify(studentDAO).isTableExist();
		verify(dataConduct).createStudents();
	}
}
