package ua.foxminded.javaspring.ServiceLayer.data.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.RandomNumber;
import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

@Component
public class StudentToCourseGenerator {

	private RandomNumber randomNumber;
	private Integer maxCountStudentCourses;
	private int countCourses = 0;

	private List<StudentAtCourse> studentAtCourses = new ArrayList<>();

	public StudentToCourseGenerator(RandomNumber randomNumber, Integer maxCountStudentCourses) {
		this.randomNumber = randomNumber;
		this.maxCountStudentCourses = maxCountStudentCourses;
	}

	public List<StudentAtCourse> addStudentToCourse(List<Student> students, int coursesCount) {
		countCourses = coursesCount;

		students.forEach(this::addToCourseByIndex);

		return studentAtCourses;
	}

	private void addToCourseByIndex(Student student) {
		for (Integer courseIndex : randomlyCoursesIndex()) {
			studentAtCourses.add(new StudentAtCourse(student, new Course(Long.valueOf(courseIndex))));
		}
	}

	private Set<Integer> randomlyCoursesIndex() {
		int randomlyQuantityCoursesOfStudent = randomNumber.generateBetweenOneAnd(maxCountStudentCourses);
		int indexOfCourse = 0;

		Set<Integer> indicesCoursesOfStudent = new HashSet<>();

		while (indicesCoursesOfStudent.size() < randomlyQuantityCoursesOfStudent) {
			indexOfCourse = randomNumber.generateBetweenOneAnd(countCourses);
			indicesCoursesOfStudent.add(indexOfCourse);
		}
		return indicesCoursesOfStudent;
	}
}
