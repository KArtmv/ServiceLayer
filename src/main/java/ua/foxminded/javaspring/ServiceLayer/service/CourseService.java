package ua.foxminded.javaspring.ServiceLayer.service;

import ua.foxminded.javaspring.ServiceLayer.model.Course;

public interface CourseService {
    boolean addCourse(Course course);

    boolean isValidCourseID(Course course);
}
