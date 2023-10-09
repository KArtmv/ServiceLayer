package ua.foxminded.javaspring.ServiceLayer.data.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.RandomNumber;
import ua.foxminded.javaspring.ServiceLayer.data.resources.CountConfig;
import ua.foxminded.javaspring.ServiceLayer.model.Course;
import ua.foxminded.javaspring.ServiceLayer.model.Student;
import ua.foxminded.javaspring.ServiceLayer.model.StudentAtCourse;

@Component
public class StudentToCourseGenerator {

    private RandomNumber randomNumber;
    private CountConfig countConfig;
    private int countCourses;

    private int maxCountCoursesOfStudent;

    private List<StudentAtCourse> studentAtCourses = new ArrayList<>();

    public StudentToCourseGenerator(RandomNumber randomNumber, CountConfig countConfig) {
        this.randomNumber = randomNumber;
        this.countConfig = countConfig;
    }

    public List<StudentAtCourse> addStudentToCourse(List<Student> students, int coursesCount) {
        countCourses = coursesCount;
        maxCountCoursesOfStudent = countConfig.getMaxCountCoursesOfStudent();

        students.forEach(this::addToCourseByIndex);

        return studentAtCourses;
    }

    private void addToCourseByIndex(Student student) {
        Set<Integer> courseIndices = randomlyCoursesIndex();

        for (Integer courseIndex : courseIndices) {
            studentAtCourses.add(new StudentAtCourse(student, new Course(Long.valueOf(courseIndex))));
        }
    }

    private Set<Integer> randomlyCoursesIndex() {
        Set<Integer> indicesCoursesOfStudent = new HashSet<>();

        int randomlyQuantityCoursesOfStudent = randomNumber.generateBetweenOneAnd(maxCountCoursesOfStudent);

        while (indicesCoursesOfStudent.size() < randomlyQuantityCoursesOfStudent) {
            indicesCoursesOfStudent.add(randomNumber.generateBetweenOneAnd(countCourses));
        }
        return indicesCoursesOfStudent;
    }
}
