package ua.foxminded.javaspring.ServiceLayer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceImplTest {

	@Mock
	private GroupDAO groupDAO;

	private GroupServiceImpl groupService;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		groupService = new GroupServiceImpl(groupDAO);
	}

	@Test
	void counterStudentsAtGroups_shouldReturnListOfCountStudentsAtGroup_whenIsCalled() {
		List<CounterStudentsAtGroup> counterStudentsAtGroup = new ArrayList<>();
		counterStudentsAtGroup.add(new CounterStudentsAtGroup(22, "someGroup1"));
		counterStudentsAtGroup.add(new CounterStudentsAtGroup(18, "someGroup2"));
		counterStudentsAtGroup.add(new CounterStudentsAtGroup(10, "someGroup3"));

		int countStudentsAtGroup = 22;

		when(groupDAO.counterStudentsAtGroups(anyInt())).thenReturn(counterStudentsAtGroup);

		List<CounterStudentsAtGroup> result = groupService.counterStudentsAtGroups(countStudentsAtGroup);

		assertThat(result).usingRecursiveComparison().isSameAs(counterStudentsAtGroup);

		verify(groupDAO).counterStudentsAtGroups(anyInt());
	}

	@Test
	void isValidGroupID_shouldReturnTrue_whenGroupGroupExistByThisID() {
		int groupID = 7;

		when(groupDAO.isValidGroupID(any(Group.class))).thenReturn(true);

		assertThat(groupService.isValidGroupID(new Group(Long.valueOf(groupID)))).isTrue();

		verify(groupDAO).isValidGroupID(any(Group.class));
	}
}
