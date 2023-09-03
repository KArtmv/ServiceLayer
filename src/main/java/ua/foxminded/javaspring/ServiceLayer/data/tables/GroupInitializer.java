package ua.foxminded.javaspring.ServiceLayer.data.tables;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

@Component
public class GroupTableInitializer {

	private GroupDAO groupDAO;

	public GroupTableInitializer(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	public void initializeGroupTablesAndData(List<Group> groups) {
		if (groupDAO.isTableExist()) {
			insertIfTableIsEmpty(groups);
		} else {
			groupDAO.createGroupTable();
			insertGroupsIntoTable(groups);
		}
	}

	private void insertIfTableIsEmpty(List<Group> groups) {
		if (groupDAO.isGroupTableEmpty()) {
			insertGroupsIntoTable(groups);
		}
	}

	private void insertGroupsIntoTable(List<Group> groups) {
		groups.forEach(groupDAO::addGroup);
	}
}
