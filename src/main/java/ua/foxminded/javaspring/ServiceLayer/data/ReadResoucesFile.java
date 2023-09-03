package ua.foxminded.javaspring.ServiceLayer.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class ReadResoucesFile {

	private ResourceLoader resourceLoader;

	public ReadResoucesFile(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public String getScript(String filePath) throws IOException {
		StringBuilder currentStatmant = new StringBuilder();
		List<String> lines = getData(filePath);
		lines.forEach(line -> currentStatmant.append(line.trim()).append("\n"));
		return currentStatmant.toString();
	}

	public List<String> getData(String filePath) throws IOException {
		List<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(getResource(filePath)))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Failed to read file; " + filePath);
		}
		return lines;
	}

	private InputStream getResource(String filePath) {
		InputStream inputStream = null;
		try {
			inputStream = resourceLoader.getResource(filePath).getInputStream();
		} catch (IOException e) {
			System.out.println("Falet to get resouce: " + filePath);
		}
		return inputStream;
	}
}
