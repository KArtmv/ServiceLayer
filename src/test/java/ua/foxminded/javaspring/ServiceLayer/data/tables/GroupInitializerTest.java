package ua.foxminded.javaspring.ServiceLayer.data.tables;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

@RunWith(MockitoJUnitRunner.class)
public class GroupInitializerTest {

	@Mock
	private GroupDAO groupDAO;

	@Mock
	private DataConduct dataConduct;

	@Mock
	private ReadResourcesFile readResourcesFile;

	@Mock
	private SQLQueryIsTableExist queryIsTableExist;

	@Mock
	private SQLQueryOfCreateTable queryOfCreateTable;

	@InjectMocks
	private GroupInitializer initializer;

	private String sqlQueryTableExist;

	private List<Group> groups;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		Group group = new Group("group");
		groups = Arrays.asList(group, group, group);
		sqlQueryTableExist = "IsTableExist";
	}

	@Test
    void initializeGroupTablesAndData_shouldCreateCourseAndInsertIntoDatabaseTable_whenGroupTableExist() {
        when(queryIsTableExist.getGroupTableExist()).thenReturn(sqlQueryTableExist);
        when(groupDAO.isTableExist(sqlQueryTableExist)).thenReturn(true);
        when(groupDAO.isGroupTableEmpty()).thenReturn(true);
        when(dataConduct.createGroups()).thenReturn(groups);

        initializer.initializeGroupTablesAndData();

        verify(queryIsTableExist).getGroupTableExist();
        verify(groupDAO).isTableExist(sqlQueryTableExist);
        verify(groupDAO).isGroupTableEmpty();
        verify(dataConduct).createGroups();
    }

	@Test
	void initializeGroupTablesAndData_shouldCreateTableCourseAndInsertIntoDatabaseTable_whenGroupTableNotExist() {
		String filePath = "table/group.txt";
		String sqlQueryCreateTable = "CreateTableQuery";

		when(queryIsTableExist.getGroupTableExist()).thenReturn(sqlQueryTableExist);
		when(groupDAO.isTableExist(sqlQueryTableExist)).thenReturn(false);
		when(queryOfCreateTable.getGroupFilePath()).thenReturn(filePath);
		when(readResourcesFile.getScript(filePath)).thenReturn(sqlQueryCreateTable);
		when(dataConduct.createGroups()).thenReturn(groups);

		initializer.initializeGroupTablesAndData();

		verify(queryIsTableExist).getGroupTableExist();
		verify(groupDAO).isTableExist(sqlQueryTableExist);
		verify(queryOfCreateTable).getGroupFilePath();
		verify(readResourcesFile).getScript(filePath);
		verify(dataConduct).createGroups();
	}
}
