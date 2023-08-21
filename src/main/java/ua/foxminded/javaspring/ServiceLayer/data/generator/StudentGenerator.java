package ua.foxminded.javaspring.ServiceLayer.data.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.RandomNumber;
import ua.foxminded.javaspring.ServiceLayer.data.ReadDataFile;
import ua.foxminded.javaspring.ServiceLayer.model.Group;
import ua.foxminded.javaspring.ServiceLayer.model.Student;

@Component
public class StudentGenerator {

	private String firstNameFilePath;
	private String lastNameFilePath;
	private RandomNumber randomNumber;
	private ReadDataFile dataFile;

	private HashSet<Student> studentsNames = new HashSet<>();
	
	public StudentGenerator(@Qualifier("firstNameFilePath") String firstNameFilePath,
			@Qualifier("lastNameFilePath") String lastNameFilePath, ReadDataFile fileOfNames,
			RandomNumber randomNumber) {
		this.firstNameFilePath = firstNameFilePath;
		this.lastNameFilePath = lastNameFilePath;
		this.randomNumber = randomNumber;
		this.dataFile = fileOfNames;
	}

	public List<Student> generate(List<Group> groups) {
		studentNameRandomCombiner();

		int countOfGroups = groups.size();

		return addRandomGroup(countOfGroups);
	}

	private void studentNameRandomCombiner() {
		List<String> firstNames = dataFile.scan(firstNameFilePath);
		List<String> lastNames = dataFile.scan(lastNameFilePath);

		int countFirstNames = firstNames.size();
		int countLastNames = lastNames.size();

		while (studentsNames.size() < 200) {
			int randomFirstNameIndex = randomNumber.generateBeetwenOneAnd(countFirstNames);
			int randomLastNameIndex = randomNumber.generateBeetwenOneAnd(countLastNames);

			if (randomFirstNameIndex != randomLastNameIndex) {
				studentsNames.add(new Student(
							firstNames.get(randomFirstNameIndex - 1), 
							lastNames.get(randomLastNameIndex - 1)));
			}
		}
	}

	private List<Student> addRandomGroup(int countOfGroups) {
		int randomGroupIndex = 0;
		Long studentID = 1L;

		List<Student> completeStudents = new ArrayList<>();

		for (Student student : studentsNames) {
			randomGroupIndex = randomNumber.generateBeetwenOneAnd(countOfGroups);
			completeStudents.add(new Student(
					studentID, 
					student.getFirstName(), 
					student.getLastName(),
					Long.valueOf(randomGroupIndex)));
			studentID++;
		}
		return completeStudents;
	}
}
