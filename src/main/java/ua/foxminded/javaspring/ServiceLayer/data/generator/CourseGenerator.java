package ua.foxminded.javaspring.ServiceLayer.data.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.ReadDataFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.ServiceLayer.model.Course;

@Component
public class CourseGenerator {

	private ReadDataFile dataFile;
	private List<Course> courses = new ArrayList<>();

	public CourseGenerator(ReadDataFile dataFile) {
		this.dataFile = dataFile;
	}

	public List<Course> create() {
		ResourcesFilesDatabaseData resourcesFiles = new ResourcesFilesDatabaseData();
		List<String> coursesName = dataFile.scan(resourcesFiles.getCoursesFile());

		Long courseID = 1L;

		for (String string : coursesName) {
			String[] courseData = string.split("_");

			courses.add(new Course(courseID, courseData[0], courseData[1]));
			courseID++;
		}
		return courses;
	}
}
