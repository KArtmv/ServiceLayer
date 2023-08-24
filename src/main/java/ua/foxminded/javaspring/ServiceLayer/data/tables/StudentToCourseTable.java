//package ua.foxminded.javaspring.ServiceLayer.data.tables;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
//import ua.foxminded.javaspring.ServiceLayer.data.CompileSqlScriptLines;
//
//@Component
//public class StudentToCourseTable {
//
//	private CourseDAO courseDAO;
//	private CompileSqlScriptLines script;
//	private String scriptFile;
//
//	public StudentToCourseTable(CourseDAO courseDAO, CompileSqlScriptLines scriptLines, @Qualifier("studentToCourseScript") String scriptFile) {
//		this.courseDAO = courseDAO;
//		this.script = scriptLines;
//		this.scriptFile = scriptFile;
//	}
//
//	public boolean isTableExist() {
//		return courseDAO.isStudentToCourseTableExist();
//	}
//
//	public void createTable() {
//		courseDAO.createStodentToCourseTable(script.compileScript(scriptFile));
//	}
//}
