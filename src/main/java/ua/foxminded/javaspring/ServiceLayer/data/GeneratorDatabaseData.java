package ua.foxminded.javaspring.ServiceLayer.data;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.ServiceLayer.data.tables.CourseInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.GroupInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.StudentInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.StudentToCourseInitializer;

@Component
public class GeneratorDatabaseData {
	@Autowired
	private GroupInitializer groupInitializer;

	@Autowired
	private CourseInitializer courseInitializer;

	@Autowired
	private StudentInitializer studentInitializer;

	@Autowired
	private StudentToCourseInitializer studentToCourseInitializer;

	@PostConstruct
	public void generateDataAndInsertIntoDatabase() {
		groupInitializer.initializeGroupTablesAndData();

		courseInitializer.initializeCourseTableAndData();

		studentInitializer.initializeStudentTableAndData();

		studentToCourseInitializer.initializeStudentToCourseTableAndData();
	}
}
