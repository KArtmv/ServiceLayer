package ua.foxminded.javaspring.ServiceLayer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ Config.class, SQLFileConfig.class })
public class SQLScriptTablesExistConfig {

	private static final String SQL_CHEK_IS_TABLE_EXIST = "SELECT EXISTS ("
			+ "SELECT 1 "
			+ "FROM information_schema.tables "
			+ "WHERE table_name = '%s')";

	private static final String STUDENT_TABLE_NAME = "students";
	private static final String GROUP_TABLE_NAME = "groups";
	private static final String COURSE_TABLE_NAME = "courses";
	private static final String STUDENT_TO_COURSE_TABLE_NAME = "studenttocourse";

	@Bean
	public String studentTableExist() {
		return String.format(SQL_CHEK_IS_TABLE_EXIST, STUDENT_TABLE_NAME);
	}

	@Bean
	public String courseTableExist() {
		return String.format(SQL_CHEK_IS_TABLE_EXIST, COURSE_TABLE_NAME);
	}

	@Bean
	public String groupTableExist() {
		return String.format(SQL_CHEK_IS_TABLE_EXIST, GROUP_TABLE_NAME);
	}

	@Bean
	public String studentToCourseTableExist() {
		return String.format(SQL_CHEK_IS_TABLE_EXIST, STUDENT_TO_COURSE_TABLE_NAME);
	}
}
