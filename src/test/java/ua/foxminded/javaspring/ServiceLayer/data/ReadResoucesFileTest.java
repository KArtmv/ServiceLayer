package ua.foxminded.javaspring.ServiceLayer.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.core.io.ResourceLoader;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(ReadResoucesFile.class)
class ReadResoucesFileTest {

	@Mock
	private ResourceLoader mockResourceLoader;

	@Mock
	private ReadResoucesFile mockResoucesFile;

	@Mock
	private BufferedReader mockBufferedReader;


	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
//		mockResoucesFile = new ReadResoucesFile(mockResourceLoader);
	}

	@Test
	void getData_shouldReturnFileNotFoundExeption_whenFileIsNotFound() throws IOException {
		when(mockBufferedReader.readLine()).thenThrow(FileNotFoundException.class);
		
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			mockBufferedReader.readLine();
		});
	}

	@Test
	void getData_shouldReturnListOfStrings_whenFoundFile() throws Exception {
		String testFilePath = "data/test.txt";

		when(mockBufferedReader.readLine()).thenReturn("test", "test", "test", null);
		
		List<String> results = new ArrayList<>();
		String result;
		
		while((result = mockBufferedReader.readLine()) != null) {
			results.add(result);
		}
			
		List<String> expect = new ArrayList<>();
		expect.add("test");
		expect.add("test");
		expect.add("test");
			
		assertThat(expect).containsAll(mockResoucesFile.getData(testFilePath));
	}

	@Test
	void getScriptTest() {
		String testFilePath = "tables/course.txt";
		String expectedScript = "CREATE TABLE IF NOT EXISTS courses\n" 
		+ "(\n"
		+ "    course_id serial PRIMARY KEY,\n" 
		+ "    course_name varchar(50) NOT NULL,\n"
		+ "    course_description varchar(100) NOT NULL\n" 
		+ ")\n" 
		+ "\n" 
		+ "TABLESPACE pg_default;";

		List<String> expectedLines = Arrays.asList(expectedScript.split("\n"));

		when(mockResoucesFile.getData(testFilePath)).thenReturn(expectedLines);

		String result = mockResoucesFile.getScript(testFilePath).trim();

		assertEquals(expectedScript.trim(), result);
	}

	@Test
	void testGetResource() throws Exception {
		String expectedResource = "test\n test \n test";
		String privateMethodName = "getDataResource";
		String testFilePath = "data/course.txt";

		InputStream inputStream = new ByteArrayInputStream(expectedResource.getBytes());

		ReadResoucesFile tested = PowerMock.createPartialMock(ReadResoucesFile.class, privateMethodName);

		PowerMock.expectPrivate(tested, privateMethodName, testFilePath).andReturn(inputStream);

		PowerMock.replay(tested);

		String actualResource = tested.getScript(testFilePath);

		PowerMock.verify(tested);

		assertThat(actualResource).isEqualTo(expectedResource);

//		ReadResoucesFile spy = PowerMockito.spy(new ReadResoucesFile(mockResourceLoader));
//
//		PowerMockito.when(spy, "getResource", anyString()).thenReturn(inputStream);
//
//		List<String> result = mockResoucesFile.getData(testFilePath);
//
//		System.out.println(result);
//
//		assertThat(result).contains(expectedResource);

	}

}
