package ua.foxminded.javaspring.ServiceLayer.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.generator.CourseGenerator;
import ua.foxminded.javaspring.ServiceLayer.data.generator.GroupGegerator;
import ua.foxminded.javaspring.ServiceLayer.data.generator.StudentGenerator;
import ua.foxminded.javaspring.ServiceLayer.data.generator.StudentToCourseGenerator;
import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Group;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

@Component
public class DataConduct {

	private StudentGenerator studentGenerator;
	private CourseGenerator courseGenerator;
	private GroupGegerator groupGegerator;
	private StudentToCourseGenerator studentToCourse;

	private List<Student> students;
	private List<Course> courses;
	private List<Group> groups;

	public DataConduct(StudentGenerator studentGenerator, CourseGenerator courseGenerator,
			GroupGegerator groupGegerator, StudentToCourseGenerator studentToCourse) {
		this.studentGenerator = studentGenerator;
		this.courseGenerator = courseGenerator;
		this.groupGegerator = groupGegerator;
		this.studentToCourse = studentToCourse;
	}

	public List<Student> createStudents() {
		students = studentGenerator.generate(groups);
		return students;
	}

	public List<Group> createGroups() {
		groups = groupGegerator.createGroups();
		return groups;
	}

	public List<Course> createCourses() {
		courses = courseGenerator.create();
		return courses;
	}

	public List<StudentAtCourse> createRelationStudentCourse() {
		return studentToCourse.addStudentToCourse(students, courses.size());
	}
}
