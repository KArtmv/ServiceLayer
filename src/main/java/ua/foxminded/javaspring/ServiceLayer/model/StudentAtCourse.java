package ua.foxminded.javaspring.ServiceLayer.model;

public class StudentAtCourse {

	private Student studentID;
	private Course courseID;

	public StudentAtCourse(Student studentID) {
		this.studentID = studentID;
	}

	public StudentAtCourse(Student studentID, Course courseID) {
		this.studentID = studentID;
		this.courseID = courseID;
	}

	public StudentAtCourse(Course courseID) {
		this.courseID = courseID;
	}

	public Student getStudentID() {
		return studentID;
	}

	public Course getCourseID() {
		return courseID;
	}
}
