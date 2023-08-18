package ua.foxminded.javaspring.ServiceLayer.model;

public class Course {

	private Long courseID;
	private String courseName;
	private String courseDiscript;

	public Course(Long courseID) {
		this.courseID = courseID;
	}

	public Course(String courseName, String courseDiscript) {
		this.courseName = courseName;
		this.courseDiscript = courseDiscript;
	}

	public Course(Long courseID, String courseName, String courseDiscript) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseDiscript = courseDiscript;
	}

	public Long getCourseID() {
		return courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseDescript() {
		return courseDiscript;
	}
}
