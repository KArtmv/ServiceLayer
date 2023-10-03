package ua.foxminded.javaspring.ServiceLayer.data.tables;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.model.Student;

@Component
public class StudentInitializer {

    private StudentDAO studentDAO;
    private DataConduct dataConduct;

    private List<Student> students = new ArrayList<>();

    public StudentInitializer(StudentDAO studentDAO, DataConduct dataConduct) {
        this.studentDAO = studentDAO;
        this.dataConduct = dataConduct;
    }

    public void initializeStudentTableAndData() {
        if (studentDAO.isTableExist()) {
            insertIfTableIsEmpty();
        } else {
            studentDAO.createStudentTable();
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

    private List<Student> generateStudentsData() {
        students = dataConduct.createStudents();
        return students;
    }
}
