package ua.foxminded.javaspring.ServiceLayer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ Config.class, SQLFileConfig.class, SQLScriptTablesExistConfig.class })
public class ResourcesDatabaseDataConfig {

	private static final String GROUPS_FILE = "src/main/resources/groups.txt";
	private static final String COURSES_FILE = "src/main/resources/courses.txt";
	private static final String FIRST_NAME_FILE = "src/main/resources/firstNames.txt";
	private static final String LAST_NAME_FILE = "src/main/resources/lastNames.txt";

	@Bean
	public String firstNameFilePath() {
		return FIRST_NAME_FILE;
	}

	@Bean
	public String lastNameFilePath() {
		return LAST_NAME_FILE;
	}

	@Bean
	public String courseFilePath() {
		return COURSES_FILE;
	}

	@Bean
	public String groupFilePath() {
		return GROUPS_FILE;
	}
}
