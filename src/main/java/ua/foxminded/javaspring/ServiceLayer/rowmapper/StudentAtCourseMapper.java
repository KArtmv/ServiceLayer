package ua.foxminded.javaspring.ServiceLayer.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

public class StudentAtCourseMapper implements RowMapper<StudentAtCourse> {

	@Override
	public StudentAtCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new StudentAtCourse(
				rs.getLong("enrollment_id"),
				new Student(rs.getString("first_name"), rs.getString("last_name")),
				new Course(rs.getString("course_name"), rs.getString("course_descroption")));
	}
}
