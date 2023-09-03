package ua.foxminded.javaspring.ServiceLayer.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.foxminded.javaspring.ServiceLayer.model.CounterStudentsAtGroup;

public class CountStudentAtGroupMapper implements RowMapper<CounterStudentsAtGroup> {

	@Override
	public CounterStudentsAtGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new CounterStudentsAtGroup(rs.getInt("coutn"), rs.getString("course_name"));
	}
}
