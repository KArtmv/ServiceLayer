package ua.foxminded.javaspring.ServiceLayer.data.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.generator.sourceData.ResourcesFilesDatabaseData;
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

    @InjectMocks
    private CourseGenerator courseGenerator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generate_shouldReturnListOfCourse_whenIsValidDataProvided() {
        String filePath = "test/test.txt";

        List<Course> expected = new ArrayList<>();
        expected.add(new Course(1L, "test", "test"));
        expected.add(new Course(2L, "test", "test"));
        expected.add(new Course(3L, "test", "test"));

        when(resourcesFiles.getCoursesFilePath()).thenReturn(filePath);
        when(readFile.getData(filePath)).thenReturn(Arrays.asList("test_test", "test_test", "test_test"));

        List<Course> result = courseGenerator.generate();

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);

        verify(resourcesFiles).getCoursesFilePath();
        verify(readFile).getData(filePath);
    }
}