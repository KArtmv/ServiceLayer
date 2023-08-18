package ua.foxminded.javaspring.ServiceLayer.data.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.ReadDataFile;
import ua.foxminded.javaspring.ServiceLayer.model.Course;

@Component
public class CourseGenerator {

	private ReadDataFile dataFile;
	private String courseFilePaph;

	public CourseGenerator(ReadDataFile dataFile, @Qualifier("courseFilePaph") String courseFilePaph) {
		this.dataFile = dataFile;
		this.courseFilePaph = courseFilePaph;
	}

	private List<Course> courses = new ArrayList<>();

	public List<Course> create() {
		List<String> coursesName = dataFile.scan(courseFilePaph);

		Long courseID = 1L;

		for (String string : coursesName) {
			String[] courseData = string.split("_");

			courses.add(new Course(courseID, courseData[0], courseData[1]));
			courseID++;
		}
		return courses;
	}
}
