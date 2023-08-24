package ua.foxminded.javaspring.ServiceLayer.data.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.RandomNumber;
import ua.foxminded.javaspring.ServiceLayer.data.ReadDataFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.ServiceLayer.model.Group;
import ua.foxminded.javaspring.ServiceLayer.model.Student;

@Component
public class StudentGenerator {

	private RandomNumber randomNumber;
	private ReadDataFile dataFile;

	private HashSet<Student> studentsNames = new HashSet<>();

	public StudentGenerator(ReadDataFile fileOfNames, RandomNumber randomNumber) {
		this.randomNumber = randomNumber;
		this.dataFile = fileOfNames;
	}

	public List<Student> generate(List<Group> groups) {
		studentNameRandomCombiner();

		int countOfGroups = groups.size();

		return addRandomGroup(countOfGroups);
	}

	private void studentNameRandomCombiner() {
		ResourcesFilesDatabaseData resourcesFiles = new ResourcesFilesDatabaseData();
		List<String> firstNames = dataFile.scan(resourcesFiles.getFirstNameFile());
		List<String> lastNames = dataFile.scan(resourcesFiles.getLastNameFile());

		int countFirstNames = firstNames.size();
		int countLastNames = lastNames.size();

		while (studentsNames.size() < 200) {
			int randomFirstNameIndex = randomNumber.generateBetweenOneAnd(countFirstNames);
			int randomLastNameIndex = randomNumber.generateBetweenOneAnd(countLastNames);

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
			randomGroupIndex = randomNumber.generateBetweenOneAnd(countOfGroups);
			completeStudents.add(new Student(
					studentID, student.getFirstName(), 
					student.getLastName(),
					Long.valueOf(randomGroupIndex)));
			studentID++;
		}
		return completeStudents;
	}
}
