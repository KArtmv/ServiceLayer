package ua.foxminded.javaspring.ServiceLayer.data.tables;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.data.CompileSqlScriptLines;

@Component
public class CourseTable {

	private CourseDAO courseDAO;
	private CompileSqlScriptLines scriptLines;
	private String scriptFile;

	public CourseTable(CourseDAO courseDAO, CompileSqlScriptLines scriptLines, @Qualifier("courseScript") String scriptFile) {
		this.courseDAO = courseDAO;
		this.scriptLines = scriptLines;
		this.scriptFile = scriptFile;
	}

	public boolean isTableExist() {
		return courseDAO.isCourseTableExist();
	}

	public void createTable() {
		courseDAO.createCourseTable(scriptLines.compileScript(scriptFile));
	}
}
