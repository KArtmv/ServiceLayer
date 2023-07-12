package ua.foxminded.javaspring.ServiceLayer.repository;

import java.security.acl.Group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, Integer> {
	Group findByName(String name);
}
