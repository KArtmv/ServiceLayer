package ua.foxminded.javaspring.ServiceLayer.options;

import ua.foxminded.javaspring.ServiceLayer.model.Course;

public interface CourseService {
    boolean addCourse(Course course);

    boolean isValidCourseID(Course course);
}
