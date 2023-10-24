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

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.data.DataConduct;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryIsTableExist;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.ServiceLayer.model.Course;

@RunWith(MockitoJUnitRunner.class)
public class CourseInitializerTest {

    @Mock
    private CourseDAO courseDAO;
    @Mock
    private DataConduct dataConduct;

    @Mock
    private ReadResourcesFile readResourcesFile;

    @Mock
    private SQLQueryIsTableExist queryIsTableExist;

    @Mock
    private SQLQueryOfCreateTable queryOfCreateTable;

    @InjectMocks
    private CourseInitializer initializer;

    private List<Course> courses;

    private String sqlQueryTableExist;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        Course course = new Course("course", "description");
        courses = Arrays.asList(course, course, course);
        sqlQueryTableExist = "IsTableExist";
    }

    @Test
    void initializeCourseTableAndData_shouldCreateCourseAndInsertIntoDatabaseTable_whenCourseTableExist() {

        when(queryIsTableExist.getCourseTableExist()).thenReturn(sqlQueryTableExist);
        when(courseDAO.isCourseTableExist(sqlQueryTableExist)).thenReturn(true);
        when(courseDAO.isCourseTableEmpty()).thenReturn(true);
        when(dataConduct.createCourses()).thenReturn(courses);

        initializer.initializeCourseTableAndData();

        verify(queryIsTableExist).getCourseTableExist();
        verify(courseDAO).isCourseTableExist(sqlQueryTableExist);
        verify(courseDAO).isCourseTableEmpty();
        verify(dataConduct).createCourses();
    }

    @Test
    void initializeCourseTableAndData_shouldCreateTableCourseAndInsertIntoDatabaseTable_whenCourseTableNotExist() {
        Course course = new Course("course", "description");
        List<Course> courses = Arrays.asList(course, course, course);
        String filePath = "table/course.txt";
        String sqlQueryCreateTable = "CreateTableQuery";

        when(queryIsTableExist.getCourseTableExist()).thenReturn(sqlQueryTableExist);
        when(courseDAO.isCourseTableExist(sqlQueryTableExist)).thenReturn(false);
        when(queryOfCreateTable.getCourseFilePath()).thenReturn(filePath);
        when(readResourcesFile.getScript(filePath)).thenReturn(sqlQueryCreateTable);
        when(dataConduct.createCourses()).thenReturn(courses);

        initializer.initializeCourseTableAndData();

        verify(queryIsTableExist).getCourseTableExist();
        verify(courseDAO).isCourseTableExist(sqlQueryTableExist);
        verify(queryOfCreateTable).getCourseFilePath();
        verify(readResourcesFile).getScript(filePath);
        verify(dataConduct).createCourses();
    }
}
