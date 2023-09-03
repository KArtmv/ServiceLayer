package ua.foxminded.javaspring.ServiceLayer.data.resources;

import org.springframework.stereotype.Component;

@Component
public class SQLScriptTablesExist {

	private static final String SQL_CHEK_IS_TABLE_EXIST = "SELECT EXISTS ("
			+ "SELECT 1 "
			+ "FROM information_schema.tables "
			+ "WHERE table_name = '%s')";

	private static final String STUDENT_TABLE_NAME = "students";
	private static final String GROUP_TABLE_NAME = "groups";
	private static final String COURSE_TABLE_NAME = "courses";
	private static final String STUDENT_TO_COURSE_TABLE_NAME = "studenttocourse";

	public String getStudentTableExist() {
		return String.format(SQL_CHEK_IS_TABLE_EXIST, STUDENT_TABLE_NAME);
	}

	public String getCourseTableExist() {
		return String.format(SQL_CHEK_IS_TABLE_EXIST, COURSE_TABLE_NAME);
	}

	public String getGroupTableExist() {
		return String.format(SQL_CHEK_IS_TABLE_EXIST, GROUP_TABLE_NAME);
	}

	public String getStudentToCourseTableExist() {
		return String.format(SQL_CHEK_IS_TABLE_EXIST, STUDENT_TO_COURSE_TABLE_NAME);
	}
}
