package ua.foxminded.javaspring.ServiceLayer.data.tables;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

@Component
public class GroupInitializer {

	private GroupDAO groupDAO;
	private DataConduct dataConduct;

	private List<Group> groups = new ArrayList<>();

	public GroupInitializer(GroupDAO groupDAO, DataConduct dataConduct) {
		this.groupDAO = groupDAO;
		this.dataConduct = dataConduct;
	}

	public void initializeGroupTablesAndData() {
		if (groupDAO.isTableExist()) {
			insertIfTableIsEmpty();
		} else {
			groupDAO.createGroupTable();
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

	private List<Group> generateGroupData() {
		groups = dataConduct.createGroups();
		return groups;
	}
}
