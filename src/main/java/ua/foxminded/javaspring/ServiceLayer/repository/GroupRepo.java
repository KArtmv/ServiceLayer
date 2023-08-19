package ua.foxminded.javaspring.ServiceLayer.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.ServiceLayer.model.Group;
import ua.foxminded.javaspring.ServiceLayer.rowmapper.CountStudentAtGroupMapper;

@Repository
public class GroupRepo implements GroupDAO {

	private JdbcTemplate jdbcTemplate;
	private String sqlQueryOfGroupTableExist;


	private static final String SQL_ADD_NEW_GROUP = "insert into groups (group_name) values (?)";
	private static final String SQL_COUNT_STUDENTS_BY_GROUPS = "select group_name, count(s.student_id) as student_count"
			+ "from students s"
			+ "join groups g on s.group_id = g.group_id"
			+ "group by g.group_name"
			+ "having count(s.student_id)<=21";

	public GroupRepo(JdbcTemplate jdbcTemplate, @Qualifier("groupTableExist") String sqlQueryOfGroupTableExist) {
		this.jdbcTemplate = jdbcTemplate;
		this.sqlQueryOfGroupTableExist = sqlQueryOfGroupTableExist;
	}

	@Override
	public List<CounterStudentsAtGroup> counterStudentsAtGroups(int count) {
		return jdbcTemplate.query(SQL_COUNT_STUDENTS_BY_GROUPS, new CountStudentAtGroupMapper(), count);
	}

	@Override
	public boolean isValidGroupID(Group group) {
		return jdbcTemplate.query(SQL_COUNT_STUDENTS_BY_GROUPS, ResultSet::next, group.getGroupID());
	}

	@Override
	public boolean addGroup(Group group) {
		return jdbcTemplate.update(SQL_ADD_NEW_GROUP, group.getGroupName()) > 0;
	}

	@Override
	public boolean isTableExist() {
		return jdbcTemplate.queryForObject(sqlQueryOfGroupTableExist, Boolean.class);
	}

	@Override
	public void createGroupTable(String sqlRequest) {
		jdbcTemplate.execute(sqlRequest);
	}
}
