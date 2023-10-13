package ua.foxminded.javaspring.ServiceLayer.service;

import java.util.List;

import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

public class GroupServiceImpl implements GroupService {

    private GroupDAO groupDAO;

    public GroupServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public List<CounterStudentsAtGroup> counterStudentsAtGroups(int count) {
        return groupDAO.counterStudentsAtGroups(count);
    }

    @Override
    public boolean isValidGroupID(Group group) {
        return groupDAO.isValidGroupID(group);
    }
}
