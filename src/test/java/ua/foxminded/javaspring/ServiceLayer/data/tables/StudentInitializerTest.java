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

import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.model.Student;

@RunWith(MockitoJUnitRunner.class)
public class StudentInitializerTest {

    @Mock
    private StudentDAO studentDAO;

    @Mock
    private DataConduct dataConduct;

    @Mock
    private ReadResourcesFile readResourcesFile;

    @Mock
    private SQLQueryIsTableExist queryIsTableExist;

    @Mock
    private SQLQueryOfCreateTable queryOfCreateTable;

    @InjectMocks
    private StudentInitializer initializer;

    private String sqlQueryTableExist;

    private List<Student> students;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        Student student = new Student("firsName", "lastName");
        students = Arrays.asList(student, student, student);
        sqlQueryTableExist = "IsTableExist";
    }

    @Test
    void initializeStudentTablesAndData_shouldCreateCourseAndInsertIntoDatabaseTable_whenGroupTableExist() {
        when(queryIsTableExist.getStudentTableExist()).thenReturn(sqlQueryTableExist);
        when(studentDAO.isTableExist(sqlQueryTableExist)).thenReturn(true);
        when(studentDAO.isTableEmpty()).thenReturn(true);
        when(dataConduct.createStudents()).thenReturn(students);

        initializer.initializeStudentTableAndData();

        verify(queryIsTableExist).getStudentTableExist();
        verify(studentDAO).isTableExist(sqlQueryTableExist);
        verify(studentDAO).isTableEmpty();
        verify(dataConduct).createStudents();
    }

    @Test
    void initializeStudentTablesAndData_shouldCreateTableCourseAndInsertIntoDatabaseTable_whenGroupTableNotExist() {
        String filePath = "table/student.txt";
        String sqlQueryCreateTable = "CreateTableQuery";

        when(queryIsTableExist.getStudentTableExist()).thenReturn(sqlQueryTableExist);
        when(studentDAO.isTableExist(sqlQueryTableExist)).thenReturn(false);
        when(queryOfCreateTable.getStudentFilePath()).thenReturn(filePath);
        when(readResourcesFile.getScript(filePath)).thenReturn(sqlQueryCreateTable);
        when(dataConduct.createStudents()).thenReturn(students);

        initializer.initializeStudentTableAndData();

        verify(queryIsTableExist).getStudentTableExist();
        verify(studentDAO).isTableExist(sqlQueryTableExist);
        verify(queryOfCreateTable).getStudentFilePath();
        verify(readResourcesFile).getScript(filePath);
        verify(dataConduct).createStudents();
    }
}
