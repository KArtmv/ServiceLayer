package ua.foxminded.javaspring.ServiceLayer.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ReadResourcesFileTest {

    @Mock
    ResourceLoader resourceLoader;

    private ReadResourcesFile resourcesFile;

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
        resourcesFile = new ReadResourcesFile(resourceLoader);
    }

    @Test
    void getData_shouldReturnListOfString_whenIsValidDataProvided() throws IOException {
        List<String> expect = Arrays.asList(SCRIPT.split("\n"));

        inputStream();

        List<String> result = resourcesFile.getData(FILE_PATH);

        assertThat(result).isEqualTo(expect);
        verify(resourceLoader).getResource(FILE_PATH);
    }

    @Test
    void getScript_shouldReturnScriptAsString_whenIsValidDataProvided() throws IOException {
        inputStream();

        String result = resourcesFile.getScript(FILE_PATH).trim();
        assertEquals(SCRIPT, result);
    }

    @Test
    void getData_shouldReturnRuntimeException_whenResourceIsNotFound() throws IOException {
        Resource resource = mock(Resource.class);

        when(resourceLoader.getResource(FILE_PATH)).thenReturn(resource);
        when(resource.getInputStream()).thenThrow(new IOException());

        assertThrows(RuntimeException.class, () -> resourcesFile.getData(FILE_PATH));

        verify(resourceLoader).getResource(FILE_PATH);
    }

    void inputStream() throws IOException {
        Resource resource = mock(Resource.class);
        InputStream inputStream = new ByteArrayInputStream(SCRIPT.getBytes(StandardCharsets.UTF_8));

        when(resourceLoader.getResource(FILE_PATH)).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(inputStream);
    }
}