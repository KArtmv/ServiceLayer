package ua.foxminded.javaspring.ServiceLayer.data.tables;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.data.CompileSqlScriptLines;

@Component
public class GroupTable {

	private GroupDAO groupDAO;
	private CompileSqlScriptLines scriptLines;
	private String scriptFile;

	public GroupTable(GroupDAO groupDAO, CompileSqlScriptLines scriptLines, @Qualifier("groupScript") String scriptFile) {
		this.groupDAO = groupDAO;
		this.scriptLines = scriptLines;
		this.scriptFile = scriptFile;
	}

	public boolean isTableExist() {
		return groupDAO.isTableExist();
	}

	public void createTable() {
		groupDAO.createGroupTable(scriptLines.compileScript(scriptFile));
	}
}
