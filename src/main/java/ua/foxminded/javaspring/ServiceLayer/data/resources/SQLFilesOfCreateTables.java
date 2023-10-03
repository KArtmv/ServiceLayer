package ua.foxminded.javaspring.ServiceLayer.data.resources;

import org.springframework.stereotype.Component;

@Component
public class SQLFilesOfCreateTables {

    private static final String SQL_SCRIPT_FILE_STUDENT = "tables/studentTable.txt";
    private static final String SQL_SCRIPT_FILE_GROUP = "tables/groupTable.txt";
    private static final String SQL_SCRIPT_FILE_COURSE = "tables/courseTable.txt";
    private static final String SQL_SCRIPT_FILE_STUDENT_TO_COURSE = "tables/studentToCourseTable.txt";

    public String getStudentFilePath() {
        return SQL_SCRIPT_FILE_STUDENT;
    }

    public String getGroupFilePath() {
        return SQL_SCRIPT_FILE_GROUP;
    }

    public String getCourseFilePath() {
        return SQL_SCRIPT_FILE_COURSE;
    }

    public String getStudentToCourseFilePath() {
        return SQL_SCRIPT_FILE_STUDENT_TO_COURSE;
    }
}
