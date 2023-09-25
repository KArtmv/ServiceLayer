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

	public String getScript(String filePath) {
		StringBuilder currentStatmant = new StringBuilder();
		List<String> lines = getData(filePath);
		lines.forEach(line -> currentStatmant.append(line.trim()).append("\n"));
		return currentStatmant.toString();
	}

	public List<String> getData(String filePath) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(getDataResource(filePath)))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File is not found: " + filePath);
		} catch (IOException e) {
			System.out.println("Failed to read the file " + filePath + ": " + e);
			e.printStackTrace();
		}
		return lines;
	}

	private InputStream getDataResource(String filePath) {
		InputStream inputStream = null;
		try {
			inputStream = resourceLoader.getResource(filePath).getInputStream();
		} catch (IOException e) {
			System.out.println("Failed to get resouce: " + filePath);
		}
		return inputStream;
	}
}
