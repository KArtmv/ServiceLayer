package ua.foxminded.javaspring.ServiceLayer.data.tables;

import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentInitializer {

    private StudentDAO studentDAO;
    private DataConduct dataConduct;

    private ReadResourcesFile readResourcesFile;

    private SQLQueryIsTableExist queryIsTableExist;

    private SQLQueryOfCreateTable queryOfCreateTable;

    private List<Student> students = new ArrayList<>();

    public StudentInitializer(StudentDAO studentDAO, DataConduct dataConduct, ReadResourcesFile readResourcesFile,
                              SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        this.studentDAO = studentDAO;
        this.dataConduct = dataConduct;
        this.readResourcesFile = readResourcesFile;
        this.queryIsTableExist = queryIsTableExist;
        this.queryOfCreateTable = queryOfCreateTable;
    }

    public void initialize() {
        if (studentDAO.isTableExist(queryIsTableExist.queryForStudentTable())) {
            checkIsTableEmptyAndPopulate();
        } else {
            studentDAO.createStudentTable(readResourcesFile.getScript(queryOfCreateTable.getStudentFilePath()));
            populateTable();
        }
    }

    private void checkIsTableEmptyAndPopulate() {
        if (studentDAO.isTableEmpty()) {
            populateTable();
        }
    }

    private void populateTable() {
        generateStudentsData();
        students.forEach(studentDAO::addStudent);
    }

    private void generateStudentsData() {
        students = dataConduct.createStudents();
    }
}
