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

import ua.foxminded.javaspring.ServiceLayer.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

@RunWith(MockitoJUnitRunner.class)
public class StudentToCourseInitializerTest {

    @Mock
    private StudentAtCourseDAO studentAtCourseDAO;

    @Mock
    private DataConduct dataConduct;

    @Mock
    private ReadResourcesFile readResourcesFile;

    @Mock
    private SQLQueryIsTableExist queryIsTableExist;

    @Mock
    private SQLQueryOfCreateTable queryOfCreateTable;

    private String sqlQueryTableExist;

    private List<StudentAtCourse> studentAtCourses;

    private StudentToCourseInitializer initializer;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        initializer = new StudentToCourseInitializer(studentAtCourseDAO, dataConduct, readResourcesFile, queryIsTableExist, queryOfCreateTable);
        StudentAtCourse studentAtCourse = new StudentAtCourse(
                new Student("firsName", "lastName"),
                new Course("course", "discription"));
        studentAtCourses = Arrays.asList(studentAtCourse, studentAtCourse, studentAtCourse);
        sqlQueryTableExist = "IsTableExist";
    }

    @Test
    void initializeStudentToCourseTableAndData_shouldCreateCourseAndInsertIntoDatabaseTable_whenStudentToCourseTableExist() {
        when(queryIsTableExist.getStudentToCourseTableExist()).thenReturn(sqlQueryTableExist);
        when(studentAtCourseDAO.isStudentToCourseTableExist(sqlQueryTableExist)).thenReturn(true);
        when(studentAtCourseDAO.isStudentToCourseTableEmpty()).thenReturn(true);
        when(dataConduct.createRelationStudentCourse()).thenReturn(studentAtCourses);

        initializer.initializeStudentToCourseTableAndData();

        verify(queryIsTableExist).getStudentToCourseTableExist();
        verify(studentAtCourseDAO).isStudentToCourseTableExist(sqlQueryTableExist);
        verify(studentAtCourseDAO).isStudentToCourseTableEmpty();
        verify(dataConduct).createRelationStudentCourse();
    }

    @Test
    void initializeStudentToCourseTableAndData_shouldCreateTableCourseAndInsertIntoDatabaseTable_whenStudentToCourseTableNotExist() {
        String filePath = "table/studentToCourse.txt";
        String sqlQueryCreateTable = "CreateTableQuery";

        when(queryIsTableExist.getStudentToCourseTableExist()).thenReturn(sqlQueryTableExist);
        when(studentAtCourseDAO.isStudentToCourseTableExist(sqlQueryTableExist)).thenReturn(false);
        when(queryOfCreateTable.getStudentToCourseFilePath()).thenReturn(filePath);
        when(readResourcesFile.getScript(filePath)).thenReturn(sqlQueryCreateTable);
        when(dataConduct.createRelationStudentCourse()).thenReturn(studentAtCourses);

        initializer.initializeStudentToCourseTableAndData();

        verify(queryIsTableExist).getStudentToCourseTableExist();
        verify(studentAtCourseDAO).isStudentToCourseTableExist(sqlQueryTableExist);
        verify(queryOfCreateTable).getStudentToCourseFilePath();
        verify(readResourcesFile).getScript(filePath);
        verify(dataConduct).createRelationStudentCourse();
    }
}
