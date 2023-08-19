package ua.foxminded.javaspring.ServiceLayer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.ServiceLayer.model.CounterStudentsAtGroup;

@Service
public interface GroupService {

	List<CounterStudentsAtGroup> counterStudentsAtGroups(int count);

}
