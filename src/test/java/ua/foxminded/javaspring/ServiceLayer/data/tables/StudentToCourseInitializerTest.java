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
import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

@RunWith(MockitoJUnitRunner.class)
public class StudentToCourseInitializerTest {

    @Mock
    private StudentAtCourseDAO studentAtCourseDAO;

    @Mock
    private DataConduct dataConduct;

    private StudentToCourseInitializer initializer;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        initializer = new StudentToCourseInitializer(studentAtCourseDAO, dataConduct);
    }

    @Test
    void initializeStudentToCourseTableAndData_whenStudentToCourseTableExist() {
        StudentAtCourse studentAtCourse = new StudentAtCourse(
                new Student("firsName", "lastName"),
                new Course("course", "discription"));
        List<StudentAtCourse> studentAtCourses = Arrays.asList(studentAtCourse, studentAtCourse, studentAtCourse);

        when(studentAtCourseDAO.isStudentToCourseTableExist()).thenReturn(true);
        when(studentAtCourseDAO.isStudentToCourseTableEmpty()).thenReturn(true);
        when(dataConduct.createRelationStudentCourse()).thenReturn(studentAtCourses);

        initializer.initializeStudentToCourseTableAndData();

        verify(studentAtCourseDAO).isStudentToCourseTableExist();
        verify(studentAtCourseDAO).isStudentToCourseTableEmpty();
        verify(dataConduct).createRelationStudentCourse();
    }

    @Test
    void initializeStudentToCourseTableAndData_whenStudentToCourseTableNotExist() {
        StudentAtCourse studentAtCourse = new StudentAtCourse(
                new Student("firsName", "lastName"),
                new Course("course", "discription"));
        List<StudentAtCourse> studentAtCourses = Arrays.asList(studentAtCourse, studentAtCourse, studentAtCourse);

        when(studentAtCourseDAO.isStudentToCourseTableExist()).thenReturn(false);
        when(dataConduct.createRelationStudentCourse()).thenReturn(studentAtCourses);

        initializer.initializeStudentToCourseTableAndData();

        verify(studentAtCourseDAO).isStudentToCourseTableExist();
        verify(dataConduct).createRelationStudentCourse();
    }
}
