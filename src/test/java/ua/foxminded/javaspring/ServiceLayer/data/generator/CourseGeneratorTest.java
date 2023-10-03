package ua.foxminded.javaspring.ServiceLayer.data.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.ServiceLayer.model.Course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CourseGeneratorTest {

    @Mock
    ResourcesFilesDatabaseData resourcesFiles;

    @Mock
    ReadResourcesFile readFile;

    @Mock
    CourseGenerator courseGenerator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generate_shouldReturnListOfCourse_whenIsCalled() {
        Course course = new Course("test", "test");
        List<Course> courses = Arrays.asList(course, course, course);

        when(courseGenerator.generate()).thenReturn(courses);

        List<Course> result = courseGenerator.generate();

        assertThat(result).containsAll(courses);

        verify(courseGenerator).generate();
    }


    @Test
    void generate_shouldReturnListOfCourse_whenIsValidDataProvided() {
        CourseGenerator courseGenerator = new CourseGenerator(readFile, resourcesFiles);

        when(resourcesFiles.getCoursesFilePath()).thenReturn("test/test.txt");
        when(readFile.getData("test/test.txt")).thenReturn(Arrays.asList("test_test", "test_test", "test_test"));

        List<Course> courses = new ArrayList<>();
        courses.add(new Course(1L, "test", "test"));
        courses.add(new Course(2L, "test", "test"));
        courses.add(new Course(3L, "test", "test"));

        List<Course> result = courseGenerator.generate();

        assertThat(result).usingRecursiveComparison().isEqualTo(courses);

        verify(resourcesFiles).getCoursesFilePath();
        verify(readFile).getData("test/test.txt");
    }
}