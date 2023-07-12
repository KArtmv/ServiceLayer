package ua.foxminded.javaspring.ServiceLayer.model;

public class Student {

	private Long studentID;
	private String firstName;
	private String lastName;
	private Long groupID;

	public Student(Long studentID) {
		this.studentID = studentID;
	}

	public Student(String firstName, String lastName, Long groupID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.groupID = groupID;
	}

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Student(Long studentID, String firstName, String lastName, Long groupID) {
		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.groupID = groupID;
	}

	public Long getStudentID() {
		return studentID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Long getGroupID() {
		return groupID;
	}

}
