package ua.foxminded.javaspring.ServiceLayer.data.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.ServiceLayer.model.Course;

@Component
public class CourseGenerator {

    private ReadResourcesFile readFile;
    private ResourcesFilesDatabaseData resourcesFiles;

    private List<Course> courses = new ArrayList<>();

    public CourseGenerator(ReadResourcesFile dataFile, ResourcesFilesDatabaseData resourcesFiles) {
        this.readFile = dataFile;
        this.resourcesFiles = resourcesFiles;
    }

    public List<Course> generate() {
        List<String> coursesName = readFile.getData(resourcesFiles.getCoursesFilePath());

        Long courseID = 1L;

        for (String string : coursesName) {
            String[] courseData = string.split("_");

            courses.add(new Course(courseID, courseData[0], courseData[1]));
            courseID++;
        }
        return courses;
    }
}
