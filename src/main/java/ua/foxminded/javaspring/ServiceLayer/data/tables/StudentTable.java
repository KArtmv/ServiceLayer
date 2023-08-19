package ua.foxminded.javaspring.ServiceLayer.data.tables;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;
import ua.foxminded.javaspring.ServiceLayer.data.CompileSqlScriptLines;

@Component
public class StudentTable {

	private StudentDAO studentDAO;
	private CompileSqlScriptLines script;
	private String scriptFile;

	public StudentTable(StudentDAO studentDAO, CompileSqlScriptLines sqlScript, @Qualifier("studentScript") String scriptFile) {
		this.studentDAO = studentDAO;
		this.script = sqlScript;
		this.scriptFile = scriptFile;
	}

	public boolean isTableExist() {
		return studentDAO.isTableExist();
	}

	public void createTable() {
		studentDAO.createStudentTable(script.compileScript(scriptFile));
	}
}
