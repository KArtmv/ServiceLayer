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
public class ReadResourcesFile {

    private ResourceLoader resourceLoader;

    public ReadResourcesFile(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String getScript(String filePath) {
        StringBuilder currentStatement = new StringBuilder();
        List<String> lines = getData(filePath);
        lines.forEach(line -> currentStatement.append(line.trim()).append("\n"));
        return currentStatement.toString();
    }

    public List<String> getData(String filePath){
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getDataResource(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("File is not found: " + filePath);
        }
        return lines;
    }

    private InputStream getDataResource(String filePath) {
        InputStream inputStream;
        try {
            inputStream = resourceLoader.getResource(filePath).getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputStream;
    }
}
