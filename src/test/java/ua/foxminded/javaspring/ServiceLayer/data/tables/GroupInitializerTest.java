package ua.foxminded.javaspring.ServiceLayer.data.tables;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

@RunWith(MockitoJUnitRunner.class)
public class GroupInitializerTest {

    @Mock
    private GroupDAO groupDAO;

    @Mock
    private DataConduct dataConduct;

    private GroupInitializer initializer;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        initializer = new GroupInitializer(groupDAO, dataConduct);
    }

    @Test
    void initializeGroupTablesAndData_whenGroupTableExist() {
        Group group = new Group("group");
        List<Group> groups = Arrays.asList(group, group, group);

        when(groupDAO.isTableExist()).thenReturn(true);
        when(groupDAO.isGroupTableEmpty()).thenReturn(true);
        when(dataConduct.createGroups()).thenReturn(groups);

        initializer.initializeGroupTablesAndData();

        verify(groupDAO).isTableExist();
        verify(groupDAO).isGroupTableEmpty();
        verify(dataConduct).createGroups();
    }

    @Test
    void initializeGroupTablesAndData_whenGroupTableNotExist() {
        Group group = new Group("group");
        List<Group> groups = Arrays.asList(group, group, group);

        when(groupDAO.isTableExist()).thenReturn(false);
        when(dataConduct.createGroups()).thenReturn(groups);

        initializer.initializeGroupTablesAndData();

        verify(groupDAO).isTableExist();
        verify(dataConduct).createGroups();
    }
}
