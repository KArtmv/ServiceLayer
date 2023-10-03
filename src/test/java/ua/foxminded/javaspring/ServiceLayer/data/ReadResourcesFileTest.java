package ua.foxminded.javaspring.ServiceLayer.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
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

@RunWith(MockitoJUnitRunner.class)
class ReadResoucesFileTest {

	@Mock
	private ReadResoucesFile mockResoucesFile;

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

		when(mockResoucesFile.getData(FILE_PATH)).thenReturn(expect);

		assertThat(expect).containsAll(mockResoucesFile.getData(FILE_PATH));

		verify(mockResoucesFile).getData(FILE_PATH);
		verify(mockResoucesFile, atLeastOnce()).getData(FILE_PATH);
	}

	@Test
	void getScript_shouldReturnSQLScriptAsString_whenFileIsFind() {
		when(mockResoucesFile.getScript(FILE_PATH)).thenReturn(SCRIPT.trim());

		String result = mockResoucesFile.getScript(FILE_PATH);

		assertEquals(SCRIPT.trim(), result);

		verify(mockResoucesFile).getScript(FILE_PATH);
		verify(mockResoucesFile, atLeastOnce()).getScript(FILE_PATH);
	}
}
