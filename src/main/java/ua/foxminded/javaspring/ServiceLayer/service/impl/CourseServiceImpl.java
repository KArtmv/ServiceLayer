package ua.foxminded.javaspring.ServiceLayer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.ServiceLayer.dao.CourseDAO;
import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseDAO courseDAO;

    @Autowired
    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);
    }

    @Override
    public boolean isValidCourseID(Course course) {
        return courseDAO.isValidCourseID(course);
    }
}
