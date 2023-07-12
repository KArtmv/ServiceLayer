package ua.foxminded.javaspring.ServiceLayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.foxminded.javaspring.ServiceLayer.model.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {
	Course findByName(String name);
}
