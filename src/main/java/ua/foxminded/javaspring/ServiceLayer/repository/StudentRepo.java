package ua.foxminded.javaspring.ServiceLayer.repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResoucesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLFilesOfCreateTables;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLScriptTablesExist;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;
import ua.foxminded.javaspring.ServiceLayer.rowmapper.StudentAtCourseMapper;
import ua.foxminded.javaspring.ServiceLayer.rowmapper.StudentMapper;

@Repository
public class StudentRepo implements StudentDAO {

	private JdbcTemplate jdbcTemplate;
	private ReadResoucesFile readFile;
	private SQLScriptTablesExist scriptTablesExist;
	private SQLFilesOfCreateTables sqlTableFile;

	private static final String SQL_ADD_NEW_STUDENT = "insert into students (first_name, last_name, group_id) values (?, ?, ?)";
	private static final String SQL_GET_STUDENT_BY_ID = "select * from student where student_id=?";
	private static final String SQL_DELETE_STUDENT_BY_ID = "delete from student where student_id=?";
	private static final String SQL_GET_LIST_COURSES_OF_STUDENT = "select"
			+ "sc.enrollment_id, s.first_name, s.last_name, c.course_name, c.course_description"
			+ "from student s join studentatcourse sc on s.student_id = sc.student_id"
			+ "join course c on sc.course_id = c.course_id" + "where student_id=?";
	private static final String SQL_CHECK_IS_STUDENT_EXIST = "select student_id from student where student_id=?";
	private static final String SQL_CHECK_IS_STUDEN_TABLE_EMPTY = "SELECT COUNT(*) FROM students";

	public StudentRepo(JdbcTemplate jdbcTemplate, ReadResoucesFile readFile, SQLScriptTablesExist scriptTablesExist,
			SQLFilesOfCreateTables sqlTableFile) {
		this.jdbcTemplate = jdbcTemplate;
		this.readFile = readFile;
		this.scriptTablesExist = scriptTablesExist;
		this.sqlTableFile = sqlTableFile;
	}

	@Override
	public Optional<Student> getStudentByID(Student student) {
		Student studentResult = jdbcTemplate.queryForObject(SQL_GET_STUDENT_BY_ID, new StudentMapper(),
				student.getStudentID());
		return Optional.ofNullable(studentResult);
	}

	@Override
	public boolean deleleteStudent(Student student) {
		return jdbcTemplate.update(SQL_DELETE_STUDENT_BY_ID, student.getStudentID()) > 0;
	}

	@Override
	public List<StudentAtCourse> studentCourses(Student student) {
		return jdbcTemplate.query(SQL_GET_LIST_COURSES_OF_STUDENT, new StudentAtCourseMapper(), student.getStudentID());
	}

	@Override
	public boolean addStudent(Student student) {
		return jdbcTemplate.update(SQL_ADD_NEW_STUDENT, student.getFirstName(), student.getLastName(),
				student.getGroupID()) > 0;
	}

	@Override
	public boolean isValidStudentID(Student student) {
		return jdbcTemplate.query(SQL_CHECK_IS_STUDENT_EXIST, ResultSet::next, student.getStudentID());
	}

	@Override
	public boolean isTableExist() {
		return jdbcTemplate.queryForObject(scriptTablesExist.getStudentTableExist(), Boolean.class);
	}

	@Override
	public void createStudentTable() {
		jdbcTemplate.execute(readFile.getScript(sqlTableFile.getStudentFilePath()));
	}

	@Override
	public boolean isTableEmpty() {
		return jdbcTemplate.queryForObject(SQL_CHECK_IS_STUDEN_TABLE_EMPTY, Integer.class) == 0;
	}
}
