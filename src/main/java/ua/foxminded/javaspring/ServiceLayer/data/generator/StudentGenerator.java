package ua.foxminded.javaspring.ServiceLayer.data.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.RandomNumber;
import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.CountConfig;
import ua.foxminded.javaspring.ServiceLayer.data.resources.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.ServiceLayer.model.Group;
import ua.foxminded.javaspring.ServiceLayer.model.Student;

@Component
public class StudentGenerator {

    private RandomNumber randomNumber;

    private ReadResourcesFile readFile;

    private ResourcesFilesDatabaseData resourcesFiles;

    private CountConfig countConfig;

    private int maxCountOfStudents;

    private HashSet<Student> studentsNames = new HashSet<>();


    public StudentGenerator(RandomNumber randomNumber, ReadResourcesFile readFile,
                            ResourcesFilesDatabaseData resourcesFiles, CountConfig countConfig) {
        this.randomNumber = randomNumber;
        this.readFile = readFile;
        this.resourcesFiles = resourcesFiles;
        this.countConfig = countConfig;
    }

    public List<Student> generate(List<Group> groups) {
        maxCountOfStudents = countConfig.getMaxCountOfStudents();

        studentNameRandomCombiner();
        int countOfGroups = groups.size();

        return addRandomGroup(countOfGroups);
    }

    private void studentNameRandomCombiner() {
        List<String> firstNames = readFile.getData(resourcesFiles.getFirstNameFilePath());
        List<String> lastNames = readFile.getData(resourcesFiles.getLastNameFilePath());

        int countFirstNames = firstNames.size();
        int countLastNames = lastNames.size();

        while (studentsNames.size() < maxCountOfStudents) {
            int randomFirstNameIndex = randomNumber.generateBetweenOneAnd(countFirstNames);
            int randomLastNameIndex = randomNumber.generateBetweenOneAnd(countLastNames);

            String firstName = firstNames.get(randomFirstNameIndex - 1);
            String lastName = lastNames.get(randomLastNameIndex - 1);

            if (!firstName.equals(lastName)) {
                studentsNames.add(
                        new Student(firstName, lastName));
            }
        }
    }

    private List<Student> addRandomGroup(int countOfGroups) {
        int randomGroupIndex;

        Long studentID = 1L;

        List<Student> generatedStudents = new ArrayList<>();

        for (Student student : studentsNames) {

            randomGroupIndex = randomNumber.generateBetweenOneAnd(countOfGroups);

            generatedStudents.add(new Student(studentID, student.getFirstName(), student.getLastName(),
                    Long.valueOf(randomGroupIndex)));
            studentID++;
        }
        return generatedStudents;
    }
}
