package ua.foxminded.javaspring.ServiceLayer.data;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.dao.GroupDAO;
import ua.foxminded.javaspring.ServiceLayer.dao.StudentDAO;

@Component
public class TablesConduct {

	private StudentDAO studentDAO;
	private GroupDAO groupDAO;
	private CourseDAO courseDAO;

	public TablesConduct(StudentDAO studentDAO, GroupDAO groupDAO, CourseDAO courseDAO) {
		this.studentDAO = studentDAO;
		this.groupDAO = groupDAO;
		this.courseDAO = courseDAO;
	}

	public boolean studentTable() {
		boolean isExist = studentDAO.isTableExist();

		if (!isExist) {
			studentDAO.createStudentTable();
		}
		return isExist;
	}

	public boolean groupTable() {
		boolean isExist = groupDAO.isTableExist();

		if (!isExist) {
			groupDAO.createGroupTable();
		}
		return isExist;
	}

	public boolean courseTable() {
		boolean isExist = courseDAO.isCourseTableExist();

		if (!isExist) {
			courseDAO.createCourseTable();
		}
		return isExist;
	}

	public boolean studentToCourseTable() {
		boolean isExist = courseDAO.isStudentToCourseTableExist();

		if (!isExist) {
			courseDAO.createStodentToCourseTable();
		}
		return isExist;
	}
}
