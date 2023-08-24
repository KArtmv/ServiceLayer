package ua.foxminded.javaspring.ServiceLayer.config;

public class ResourcesFilesDatabaseData {

	private static final String GROUPS_FILE = "src/main/resources/groups.txt";
	private static final String COURSES_FILE = "src/main/resources/courses.txt";
	private static final String FIRST_NAME_FILE = "src/main/resources/firstNames.txt";
	private static final String LAST_NAME_FILE = "src/main/resources/lastNames.txt";

	public String getGroupsFile() {
		return GROUPS_FILE;
	}

	public String getCoursesFile() {
		return COURSES_FILE;
	}

	public String getFirstNameFile() {
		return FIRST_NAME_FILE;
	}

	public String getLastNameFile() {
		return LAST_NAME_FILE;
	}
}
