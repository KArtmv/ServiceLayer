package ua.foxminded.javaspring.ServiceLayer.data.resources;

public class ResourcesFilesDatabaseData {
	private static final String GROUPS_FILE = "data/groups.txt";
	private static final String COURSES_FILE = "data/courses.txt";
	private static final String FIRST_NAME_FILE = "data/firstNames.txt";
	private static final String LAST_NAME_FILE = "data/lastNames.txt";

	public String getGroupsFilePath() {
		return GROUPS_FILE;
	}

	public String getCoursesFilePath() {
		return COURSES_FILE;
	}

	public String getFirstNameFilePath() {
		return FIRST_NAME_FILE;
	}

	public String getLastNameFilePath() {
		return LAST_NAME_FILE;
	}
}
