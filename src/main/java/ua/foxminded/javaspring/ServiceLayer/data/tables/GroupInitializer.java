package ua.foxminded.javaspring.ServiceLayer.data.tables;

import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

import java.util.List;

public class GroupInitializer {

    private GroupDAO groupDAO;

    private DataConduct dataConduct;

    private ReadResourcesFile readResourcesFile;

    private SQLQueryIsTableExist queryIsTableExist;

    private SQLQueryOfCreateTable queryOfCreateTable;

    private List<Group> groups;

    public GroupInitializer(GroupDAO groupDAO, DataConduct dataConduct, ReadResourcesFile readResourcesFile,
                            SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        this.groupDAO = groupDAO;
        this.dataConduct = dataConduct;
        this.readResourcesFile = readResourcesFile;
        this.queryIsTableExist = queryIsTableExist;
        this.queryOfCreateTable = queryOfCreateTable;
    }

    public void initialize() {
        if (groupDAO.isTableExist(queryIsTableExist.queryForGroupTable())) {
            checkIsTableEmptyAndPopulate();
        } else {
            groupDAO.createGroupTable(readResourcesFile.getScript(queryOfCreateTable.getGroupFilePath()));
            populateTable();
        }
    }

    private void checkIsTableEmptyAndPopulate() {
        if (groupDAO.isGroupTableEmpty()) {
            populateTable();
        }
    }

    private void populateTable() {
        generateGroupData();
        groups.forEach(groupDAO::addGroup);
    }

    private void generateGroupData() {
        groups = dataConduct.createGroups();
    }
}
