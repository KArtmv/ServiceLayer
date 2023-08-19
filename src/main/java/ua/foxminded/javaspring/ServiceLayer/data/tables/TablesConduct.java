package ua.foxminded.javaspring.ServiceLayer.data.tables;

import org.springframework.stereotype.Component;

@Component
public class TablesConduct {

	private StudentTable studentTable;
	private GroupTable groupTable;
	private CourseTable courseTable;
	private StudentToCourseTable studentToCourseTable;

	public TablesConduct(StudentTable studentTable, GroupTable groupTable, CourseTable courseTable,
			StudentToCourseTable studentToCourseTable) {
		this.studentTable = studentTable;
		this.groupTable = groupTable;
		this.courseTable = courseTable;
		this.studentToCourseTable = studentToCourseTable;
	}

	public boolean studentTable() {
		boolean isExist = studentTable.isTableExist();

		if (!isExist) {
			studentTable.createTable();
		}
		return isExist;
	}

	public boolean groupTable() {
		boolean isExist = groupTable.isTableExist();

		if (!isExist) {
			groupTable.createTable();
		}
		return isExist;
	}

	public boolean courseTable() {
		boolean isExist = courseTable.isTableExist();

		if (!isExist) {
			courseTable.createTable();
		}
		return isExist;
	}

	public boolean studentToCourseTable() {
		boolean isExist = studentToCourseTable.isTableExist();

		if (!isExist) {
			studentToCourseTable.createTable();
		}
		return isExist;
	}
}
