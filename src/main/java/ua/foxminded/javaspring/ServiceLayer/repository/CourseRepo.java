package ua.foxminded.javaspring.ServiceLayer.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;
import ua.foxminded.javaspring.ServiceLayer.rowmapper.StudentAtCourseMapper;

@Repository
public class CourseRepo implements CourseDAO {

	private JdbcTemplate jdbcTemplate;
	private String sqlQueryOfCourseTableExist;
	private String sqlQueryOfStudentToCourseTableExist;

	private static final String SQL_ADD_NEW_COURSE = "insert into courses (course_name, course_description) values (?, ?)";
	private static final String SQL_GET_ALL_STUDENT_FROM_COURSE = "select c.course_name, c.course_description, s.first_name, s.last_name"
			+ "from studenttocourse sc" 
			+ "join students s on s.student_id = sc.student_id"
			+ "join courses c on c.course_id = sc.course_id" 
			+ "where c.course_id=1";
	private static final String SQL_ADD_STUDENT_TO_COURSE = "insert into studenttocourse (student_id, course_id) values (?, ?)";
	private static final String SQL_REMOVE_STUDENT_FROM_COURSE = "delete from studentatcourse where enrollment_id=?";
	private static final String SQL_REMOVE_STUDENT_FROM_ALL_THEIR_COURSES = "delete from studentatcourse where student_id=?";
	private static final String SQL_CHECK_IS_COURSE_EXIST = "select course_id from course where course_id=?";

	public CourseRepo(JdbcTemplate jdbcTemplate, @Qualifier("courseTableExist") String sqlQueryOfCourseTableExist,
			@Qualifier("studentToCourseTableExist") String sqlQueryOfStudentToCourseTableExist) {
		this.jdbcTemplate = jdbcTemplate;
		this.sqlQueryOfCourseTableExist = sqlQueryOfCourseTableExist;
		this.sqlQueryOfStudentToCourseTableExist = sqlQueryOfStudentToCourseTableExist;
	}

	@Override
	public List<StudentAtCourse> courseStudents(Course course) {
		return jdbcTemplate.query(SQL_GET_ALL_STUDENT_FROM_COURSE, new StudentAtCourseMapper(), course.getCourseID());
	}

	@Override
	public boolean addStodentToCourse(Student student, Course course) {
		return jdbcTemplate.update(SQL_ADD_STUDENT_TO_COURSE, student.getStudentID(), course.getCourseID()) > 0;
	}

	@Override
	public boolean removeStudentFromCourse(StudentAtCourse studentAtCourse) {
		return jdbcTemplate.update(SQL_REMOVE_STUDENT_FROM_COURSE, studentAtCourse.getEnrollmentID()) > 0;
	}

	@Override
	public boolean removeStudentFromAllTheirCourses(Student student) {
		return jdbcTemplate.update(SQL_REMOVE_STUDENT_FROM_ALL_THEIR_COURSES, student.getStudentID()) > 0;
	}

	@Override
	public boolean isValidCourseID(Course course) {
		return jdbcTemplate.query(SQL_CHECK_IS_COURSE_EXIST, ResultSet::next, course.getCourseID());
	}

	@Override
	public boolean addCourse(Course course) {
		return jdbcTemplate.update(SQL_ADD_NEW_COURSE, course.getCourseName(), course.getCourseDescript()) > 0;
	}

	@Override
	public boolean isCourseTableExist() {
		return jdbcTemplate.queryForObject(sqlQueryOfCourseTableExist, Boolean.class);
	}

	@Override
	public boolean isStudentToCourseTableExist() {
		return jdbcTemplate.queryForObject(sqlQueryOfStudentToCourseTableExist, Boolean.class);
	}

	@Override
	public void createCourseTable(String sqlScript) {
		jdbcTemplate.execute(sqlScript);
	}

	@Override
	public void createStodentToCourseTable(String sqlScript) {
		jdbcTemplate.execute(sqlScript);
	}
}
