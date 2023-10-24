package ua.foxminded.javaspring.ServiceLayer.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.data.tables.CourseInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.GroupInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.StudentInitializer;
import ua.foxminded.javaspring.ServiceLayer.data.tables.StudentToCourseInitializer;

import javax.annotation.PostConstruct;

@Component
public class GeneratorDatabaseData {

	private GroupDAO groupDAO;

	private CourseDAO courseDAO;

	private StudentDAO studentDAO;

	private StudentAtCourseDAO studentAtCourseDAO;

	private DataConduct dataConduct;

	private ReadResourcesFile readResourcesFile;

	private SQLQueryOfCreateTable createTable;

	private SQLQueryIsTableExist isTableExist;

	@Autowired
	public GeneratorDatabaseData(GroupDAO groupDAO, CourseDAO courseDAO, StudentDAO studentDAO,
			StudentAtCourseDAO studentAtCourseDAO, DataConduct dataConduct, ReadResourcesFile readResourcesFile,
			SQLQueryOfCreateTable createTable, SQLQueryIsTableExist isTableExist) {
		this.groupDAO = groupDAO;
		this.courseDAO = courseDAO;
		this.studentDAO = studentDAO;
		this.studentAtCourseDAO = studentAtCourseDAO;
		this.dataConduct = dataConduct;
		this.readResourcesFile = readResourcesFile;
		this.createTable = createTable;
		this.isTableExist = isTableExist;
	}

	@PostConstruct
	public void generateDataAndInsertIntoDatabase() {
		GroupInitializer groupInitializer = new GroupInitializer(groupDAO, dataConduct, readResourcesFile, isTableExist,
				createTable);
		groupInitializer.initializeGroupTablesAndData();

		CourseInitializer courseInitializer = new CourseInitializer(courseDAO, dataConduct, readResourcesFile,
				isTableExist, createTable);
		courseInitializer.initializeCourseTableAndData();

		StudentInitializer studentInitializer = new StudentInitializer(studentDAO, dataConduct, readResourcesFile,
				isTableExist, createTable);
		studentInitializer.initializeStudentTableAndData();

		StudentToCourseInitializer studentToCourseInitializer = new StudentToCourseInitializer(studentAtCourseDAO,
				dataConduct, readResourcesFile, isTableExist, createTable);
		studentToCourseInitializer.initializeStudentToCourseTableAndData();
	}
}
