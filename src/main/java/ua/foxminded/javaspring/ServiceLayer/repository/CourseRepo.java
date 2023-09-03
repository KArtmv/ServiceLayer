package ua.foxminded.javaspring.ServiceLayer.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResoucesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLFilesOfCreateTables;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLScriptTablesExist;
import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;
import ua.foxminded.javaspring.ServiceLayer.rowmapper.StudentAtCourseMapper;

@Repository
public class CourseRepo implements CourseDAO {

	private JdbcTemplate jdbcTemplate;
	private ReadResoucesFile readFile;
	private SQLScriptTablesExist scriptTablesExist;
	private SQLFilesOfCreateTables sqlTableFile;

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
	private static final String SQL_CHECK_IS_COURSE_TABLE_EMPTY = "SELECT COUNT(*) FROM courses";
	private static final String SQL_CHECK_IS_STUDENT_TO_COURSE_TABLE_EMPTY = "SELECT COUNT(*) FROM studenttocourse";

	public CourseRepo(JdbcTemplate jdbcTemplate, ReadResoucesFile readFile, SQLScriptTablesExist scriptTablesExist,
			SQLFilesOfCreateTables sqlTableFile) {
		this.jdbcTemplate = jdbcTemplate;
		this.readFile = readFile;
		this.scriptTablesExist = scriptTablesExist;
		this.sqlTableFile = sqlTableFile;
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
		return jdbcTemplate.queryForObject(scriptTablesExist.getCourseTableExist(), Boolean.class);
	}

	@Override
	public boolean isStudentToCourseTableExist() {
		return jdbcTemplate.queryForObject(scriptTablesExist.getStudentToCourseTableExist(), Boolean.class);
	}

	@Override
	public void createCourseTable() {
		jdbcTemplate.execute(readFile.getScript(sqlTableFile.getCourseFilePath()));
	}

	@Override
	public void createStodentToCourseTable() {
		jdbcTemplate.execute(readFile.getScript(sqlTableFile.getStudentToCourseFilePath()));
	}

	@Override
	public boolean isCourseTableEmpty() {
		boolean isEmpty = jdbcTemplate.queryForObject(SQL_CHECK_IS_COURSE_TABLE_EMPTY, Integer.class) == 0;
		System.out.println("Table is Empty?-" + isEmpty);

		return isEmpty;
		
	}

	@Override
	public boolean isStudentToCourseTableEmpty() {
		return jdbcTemplate.queryForObject(SQL_CHECK_IS_STUDENT_TO_COURSE_TABLE_EMPTY, Integer.class) > 0;
	}
}
