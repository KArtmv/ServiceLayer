package ua.foxminded.javaspring.ServiceLayer.repository;

import java.sql.ResultSet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLFilesOfCreateTables;
import ua.foxminded.javaspring.ServiceLayer.data.resources.SQLScriptTablesExist;
import ua.foxminded.javaspring.ServiceLayer.model.Course;

@Repository
public class CourseRepo implements CourseDAO {

    private JdbcTemplate jdbcTemplate;
    private ReadResourcesFile readFile;
    private SQLScriptTablesExist scriptTablesExist;
    private SQLFilesOfCreateTables sqlTableFile;

    private static final String SQL_ADD_NEW_COURSE = "insert into courses (course_name, course_description) values (?, ?)";
    private static final String SQL_CHECK_IS_COURSE_EXIST = "select course_id from course where course_id=?";
    private static final String SQL_CHECK_IS_COURSE_TABLE_EMPTY = "SELECT COUNT(*) FROM courses";

    public CourseRepo(JdbcTemplate jdbcTemplate, ReadResourcesFile readFile, SQLScriptTablesExist scriptTablesExist,
                      SQLFilesOfCreateTables sqlTableFile) {
        this.jdbcTemplate = jdbcTemplate;
        this.readFile = readFile;
        this.scriptTablesExist = scriptTablesExist;
        this.sqlTableFile = sqlTableFile;
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
    public void createCourseTable() {
        jdbcTemplate.execute(readFile.getScript(sqlTableFile.getCourseFilePath()));
    }

    @Override
    public boolean isCourseTableEmpty() {
        return jdbcTemplate.queryForObject(SQL_CHECK_IS_COURSE_TABLE_EMPTY, Integer.class) == 0;
    }
}
