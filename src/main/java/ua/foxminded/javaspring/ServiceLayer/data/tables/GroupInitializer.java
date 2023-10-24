package ua.foxminded.javaspring.ServiceLayer.data.tables;

import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

import java.util.List;

public class GroupInitializer {

	private GroupDAO groupDAO;

	private DataConduct dataConduct;

	private ReadResourcesFile readResourcesFile;

	private SQLQueryIsTableExist isTableExist;

	private SQLQueryOfCreateTable createTable;

	private List<Group> groups;

	public GroupInitializer(GroupDAO groupDAO, DataConduct dataConduct, ReadResourcesFile readResourcesFile,
			SQLQueryIsTableExist isTableExist, SQLQueryOfCreateTable createTable) {
		this.groupDAO = groupDAO;
		this.dataConduct = dataConduct;
		this.readResourcesFile = readResourcesFile;
		this.isTableExist = isTableExist;
		this.createTable = createTable;
	}

	public void initializeGroupTablesAndData() {
		if (groupDAO.isTableExist(isTableExist.getGroupTableExist())) {
			insertIfTableIsEmpty();
		} else {
			groupDAO.createGroupTable(readResourcesFile.getScript(createTable.getGroupFilePath()));
			insertGroupsIntoTable();
		}
	}

	private void insertIfTableIsEmpty() {
		if (groupDAO.isGroupTableEmpty()) {
			insertGroupsIntoTable();
		}
	}

	private void insertGroupsIntoTable() {
		generateGroupData();
		groups.forEach(groupDAO::addGroup);
	}

	private void generateGroupData() {
		groups = dataConduct.createGroups();
	}
}
