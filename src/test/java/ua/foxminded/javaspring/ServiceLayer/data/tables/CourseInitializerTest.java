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

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.model.Course;

@RunWith(MockitoJUnitRunner.class)
public class CourseInitializerTest {

	@Mock
	private CourseDAO courseDAO;
	@Mock
	private DataConduct dataConduct;

	private CourseInitializer initializer;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		initializer = new CourseInitializer(courseDAO, dataConduct);
	}

	@Test
	void initializeCourseTableAndData_whenCourseTableExist() {
		Course course = new Course("course", "discription");
		List<Course> courses = Arrays.asList(course, course, course);

		when(courseDAO.isCourseTableExist()).thenReturn(true);
		when(courseDAO.isCourseTableEmpty()).thenReturn(true);
		when(dataConduct.createCourses()).thenReturn(courses);

		initializer.initializeCourseTableAndData();

		verify(courseDAO).isCourseTableExist();
		verify(courseDAO).isCourseTableEmpty();
		verify(dataConduct).createCourses();
	}

	@Test
	void initializeCourseTableAndData_whenCourseTableNotExist() {
		Course course = new Course("course", "discription");
		List<Course> courses = Arrays.asList(course, course, course);

		when(courseDAO.isCourseTableExist()).thenReturn(false);
		when(dataConduct.createCourses()).thenReturn(courses);

		initializer.initializeCourseTableAndData();

		verify(courseDAO).isCourseTableExist();
		verify(dataConduct).createCourses();
	}
}
