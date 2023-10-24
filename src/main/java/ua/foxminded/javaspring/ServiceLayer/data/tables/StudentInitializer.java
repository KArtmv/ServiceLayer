package ua.foxminded.javaspring.ServiceLayer.data.tables;

import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentInitializer {

    private StudentDAO studentDAO;
    private DataConduct dataConduct;

    private ReadResourcesFile readResourcesFile;

    private SQLQueryIsTableExist isTableExist;

    private SQLQueryOfCreateTable createTable;

    private List<Student> students = new ArrayList<>();

    public StudentInitializer(StudentDAO studentDAO, DataConduct dataConduct, ReadResourcesFile readResourcesFile,
                              SQLQueryIsTableExist isTableExist, SQLQueryOfCreateTable createTable) {
        this.studentDAO = studentDAO;
        this.dataConduct = dataConduct;
        this.readResourcesFile = readResourcesFile;
        this.isTableExist = isTableExist;
        this.createTable = createTable;
    }

    public void initializeStudentTableAndData() {
        if (studentDAO.isTableExist(isTableExist.getStudentTableExist())) {
            insertIfTableIsEmpty();
        } else {
            studentDAO.createStudentTable(readResourcesFile.getScript(createTable.getStudentFilePath()));
            insertStudentsIntoTable();
        }
    }

    private void insertIfTableIsEmpty() {
        if (studentDAO.isTableEmpty()) {
            insertStudentsIntoTable();
        }
    }

    private void insertStudentsIntoTable() {
        generateStudentsData();
        students.forEach(studentDAO::addStudent);
    }

    private void generateStudentsData() {
        students = dataConduct.createStudents();
    }
}
