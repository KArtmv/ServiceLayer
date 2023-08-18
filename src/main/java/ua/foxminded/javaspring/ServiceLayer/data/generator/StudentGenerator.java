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

	private String firstNameFilePaph;
	private String lastNameFilePaph;
	private RandomNumber randomNumber;
	private ReadDataFile dataFile;

	public StudentGenerator(@Qualifier("firstNameFilePaph") String firstNameFilePaph,
			@Qualifier("lastNameFilePaph") String lastNameFilePaph, ReadDataFile fileOfNames,
			RandomNumber randomNumber) {
		this.firstNameFilePaph = firstNameFilePaph;
		this.lastNameFilePaph = lastNameFilePaph;
		this.randomNumber = randomNumber;
		this.dataFile = fileOfNames;
	}

	private HashSet<Student> studentsNames = new HashSet<>();
	private List<Student> completeStudents = new ArrayList<>();

	public List<Student> generate(List<Group> groups) {
		studentNameRandomCombiner();

		int countOfGroups = groups.size();
		addRandomGroup(countOfGroups);

		return completeStudents;
	}

	private void studentNameRandomCombiner() {
		List<String> firstNames = dataFile.scan(firstNameFilePaph);
		List<String> lastNames = dataFile.scan(lastNameFilePaph);

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

	private void addRandomGroup(int countOfGroups) {
		int randomGroupIndex = 0;
		Long studentID =  1L;

		for (Student student : studentsNames) {
			randomGroupIndex = randomNumber.generateBeetwenOneAnd(countOfGroups);
			completeStudents.add(new Student(
					studentID, 
					student.getFirstName(), 
					student.getLastName(),
					Long.valueOf(randomGroupIndex)));
			studentID++;
		}
	}
}
