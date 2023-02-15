package util;

import java.util.*;
import java.util.stream.Collectors;

import model.*;

public class Students {

    // Holds the collection of student for the methods to operate on
    private final Collection<Student> students;

    // Private constructor, used by the factory method
    private Students(Student students) {
        this.students = List.of(students);
    }

    // Public factory method
    public static Students students(Student students) {
        return new Students(students);
    }

    // Return all students that start(ed) between the the two
    // given years (inclusive of from and to)
    public Collection<Student> startingBetween(int from, int to) {
        return students.stream()
                .filter(s -> s.getStartYear().getValue() >= from && s.getStartYear().getValue() <= to)
                .collect(Collectors.toList());
    }

    // Return all students that finish(ed) between the the two given
    // years (inclusive of from and to).
    public Collection<Student> finishingBetween(int from, int to) {
        return students.stream()
                .filter(s -> s.getFinishYear().isPresent()
                        && s.getFinishYear().get().getValue() >= from
                        && s.getFinishYear().get().getValue() <= to)
                .collect(Collectors.toList());
    }

    // Return all students grouped by start year.
    // The result should be a Map from school year to a collection of students.
    public Map<SchoolYear, ? extends Collection<Student>> groupedByStartYear() {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getStartYear));
    }

    // For the given school year, return counts of the number of students for
    // each course length (for students starting that year).
    // The result should be a Map from course length to a count of students.
    public Map<Integer, Long> countOfCourseLengthforStartYear(SchoolYear startYear) {
        return students.stream()
                .filter(s -> s.getStartYear().equals(startYear))
                .collect(Collectors.groupingBy(Student::getCourseLength, Collectors.counting()));
    }

    // For the given school year, return counts of the number of students for
    // each country code (for students starting that year).
    // The result should be a Map from country code to a count of students.
    public Map<String, Long> countOfCountryCodeForStartYear(SchoolYear startYear) {
        return students.stream()
                .filter(s -> s.getStartYear().equals(startYear))
                .collect(Collectors.groupingBy(Student::getCountryCode, Collectors.counting()));
    }

    // Return a list of students sorted in ascending order by id
    public List<Student> orderedById() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getId))
                .collect(Collectors.toList());
    }

    // Return a list of students sorted in ascending order by name,
    // if two students have the same name, they should be sorted by id
    public List<Student> orderedByNameThenId() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getName).thenComparing(Student::getId))
                .collect(Collectors.toList());
    }

}
