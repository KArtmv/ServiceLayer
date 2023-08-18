package ua.foxminded.javaspring.ServiceLayer.data;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CompileSqlScriptLines {

	private ReadDataFile dataFile;

	public CompileSqlScriptLines(ReadDataFile dataFile) {
		this.dataFile = dataFile;
	}

	public String compileScript(String fileName) {
		StringBuilder stringBuilder = new StringBuilder();

		List<String> strings = dataFile.scan(fileName);
		strings.forEach(stringBuilder::append);

		return stringBuilder.toString();
	}
}
