package ua.foxminded.javaspring.ServiceLayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.foxminded.javaspring.ServiceLayer.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	Student findByName(String name);
}
