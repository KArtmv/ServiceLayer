package ua.foxminded.javaspring.ServiceLayer.dao;

import java.util.List;

import ua.foxminded.javaspring.ServiceLayer.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

public interface GroupDAO {
	boolean addGroup(Group group);

	List<CounterStudentsAtGroup> counterStudentsAtGroups(int count);

	boolean isValidGroupID(Group group);
	
	boolean isTableExist();
	
	void createGroupTable();

	boolean isGroupTableEmpty();
}
