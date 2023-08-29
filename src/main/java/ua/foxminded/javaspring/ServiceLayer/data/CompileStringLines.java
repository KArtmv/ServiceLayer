package ua.foxminded.javaspring.ServiceLayer.data;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CompileStringLines {

	private ReadDataFile dataFile;

	public CompileStringLines(ReadDataFile dataFile) {
		this.dataFile = dataFile;
	}

	public String compile(String fileName) {
		StringBuilder stringBuilder = new StringBuilder();

		List<String> strings = dataFile.scan(fileName);
		strings.forEach(stringBuilder::append);

		return stringBuilder.toString();
	}
}
