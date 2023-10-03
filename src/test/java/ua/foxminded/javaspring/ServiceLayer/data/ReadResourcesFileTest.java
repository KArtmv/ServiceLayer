package ua.foxminded.javaspring.ServiceLayer.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ReadResourcesFileTest {

    @Mock
    ResourceLoader resourceLoader;

    @Mock
    private ReadResourcesFile mockResourcesFile;

    private static final String FILE_PATH = "tables/course.txt";
    private static final String SCRIPT = "CREATE TABLE IF NOT EXISTS courses\n"
            + "(\n"
            + "course_id serial PRIMARY KEY,\n"
            + "course_name varchar(50) NOT NULL,\n"
            + "course_description varchar(100) NOT NULL\n"
            + ")\n"
            + "\n"
            + "TABLESPACE pg_default;";

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getData_shouldReturnListOfStrings_whenFileIsFound() throws Exception {
        List<String> expect = Arrays.asList(SCRIPT.split("\n"));

        when(mockResourcesFile.getData(FILE_PATH)).thenReturn(expect);

        assertThat(expect).containsAll(mockResourcesFile.getData(FILE_PATH));

        verify(mockResourcesFile).getData(FILE_PATH);
        verify(mockResourcesFile, atLeastOnce()).getData(FILE_PATH);
    }

    @Test
    void getScript_shouldReturnSQLScriptAsString_whenFileIsFind() {
        when(mockResourcesFile.getScript(FILE_PATH)).thenReturn(SCRIPT.trim());

        String result = mockResourcesFile.getScript(FILE_PATH);

        assertEquals(SCRIPT.trim(), result);

        verify(mockResourcesFile).getScript(FILE_PATH);
        verify(mockResourcesFile, atLeastOnce()).getScript(FILE_PATH);
    }

    @Test
    void getData_shouldReturnListOfString_whenIsValidDataProvided() throws IOException {
        ReadResourcesFile resourcesFile = new ReadResourcesFile(resourceLoader);
        Resource resource = mock(Resource.class);
        InputStream inputStream = new ByteArrayInputStream(SCRIPT.getBytes(StandardCharsets.UTF_8));
        List<String> expect = Arrays.asList(SCRIPT.split("\n"));


        when(resourceLoader.getResource(FILE_PATH)).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(inputStream);

        List<String> result = resourcesFile.getData(FILE_PATH);

        assertThat(result).isEqualTo(expect);
        verify(resourceLoader).getResource(FILE_PATH);
    }
}