package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class StudentGroup {
    private final Logger logger = LogManager.getLogger();
    private final MyArrayList<Student> students = new MyArrayList<>();

    public Optional<Student> getByName(String name) {
        return students.stream()
                .filter(student -> student.getName().equals(name))
                .findFirst();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
        logger.info("Student {} added to group", student.getName());
    }

    public void showGroupList() {
        if (students.isEmpty()) {
            System.out.println("Нет студентов в группе");
            return;
        }

        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ") " + students.get(i).getName());
        }
    }

    public void showGroupAbsences() {
        if (students.isEmpty()) {
            System.out.println("Нет студентов в группе");
            return;
        }

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println((i + 1) + ") " + student.getName() + " " + student.getAbsences());
        }
    }
}
