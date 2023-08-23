package ua.foxminded.javaspring.ServiceLayer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Config.class)
public class SQLFileConfig {

	private static final String SQL_SCRIPT_FILE_STUDENT = "src/main/resources/studentTable.txt";
	private static final String SQL_SCRIPT_FILE_GROUP = "src/main/resources/groupTable.txt";
	private static final String SQL_SCRIPT_FILE_COURSE = "src/main/resources/courseTable.txt";
	private static final String SQL_SCRIPT_FILE_STUDENT_TO_COURSE = "src/main/resources/studentToCourseTable.txt";

	@Bean
	public String studentScript() {
		return SQL_SCRIPT_FILE_STUDENT;
	}

	@Bean
	public String groupScript() {
		return SQL_SCRIPT_FILE_GROUP;
	}

	@Bean
	public String courseScript() {
		return SQL_SCRIPT_FILE_COURSE;
	}

	@Bean
	public String studentToCourseScript() {
		return SQL_SCRIPT_FILE_STUDENT_TO_COURSE;
	}
}
