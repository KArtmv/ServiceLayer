package ua.foxminded.javaspring.ServiceLayer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.ServiceLayer.model.Group;
import ua.foxminded.javaspring.ServiceLayer.service.GroupService;

@Service
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
