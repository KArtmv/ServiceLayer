package ua.foxminded.javaspring.ServiceLayer.data.resources;

public class SQLFilesOfCreateTables {

	private static final String SQL_SCRIPT_FILE_STUDENT = "src/main/resources/tables/studentTable.txt";
	private static final String SQL_SCRIPT_FILE_GROUP = "src/main/resources/tables/groupTable.txt";
	private static final String SQL_SCRIPT_FILE_COURSE = "src/main/resources/tables/courseTable.txt";
	private static final String SQL_SCRIPT_FILE_STUDENT_TO_COURSE = "src/main/resources/tables/studentToCourseTable.txt";

	public String getSqlScriptFileStudent() {
		return SQL_SCRIPT_FILE_STUDENT;
	}

	public String getSqlScriptFileGroup() {
		return SQL_SCRIPT_FILE_GROUP;
	}

	public String getSqlScriptFileCourse() {
		return SQL_SCRIPT_FILE_COURSE;
	}

	public String getSqlScriptFileStudentToCourse() {
		return SQL_SCRIPT_FILE_STUDENT_TO_COURSE;
	}
}
