package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.example.ScannerManager.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        StudentGroup group = new StudentGroup();

        mainLoop:
        while (true) {
            System.out.println("Выберите действие");
            System.out.println("1) Добавить студента");
            System.out.println("2) Добавить пропуск");
            System.out.println("3) Просмотреть пропуски группы");
            System.out.println("0) Выход");
            int choice = scanInt();

            switch (choice) {
                case 0 -> {
                    break mainLoop;
                }
                case 1 -> {
                    System.out.println("Введите фамилию студента");
                    String surname = scanString();
                    group.addStudent(new Student(surname));
                }
                case 2 -> {
                    if (group.getStudents().isEmpty()) {
                        System.out.println("Еще нет студентов");
                        break;
                    }

                    System.out.println("Выберите студента");
                    group.showGroupList();
                    int studentIndex = scanInt() - 1;
                    Student student = group.getStudents().get(studentIndex);

                    System.out.println("Введите дату пропуска");
                    student.addAbsence(scanLocalDate());
                }
                case 3 -> {
                    group.showGroupAbsences();
                }
                default -> {
                    System.out.println("Невозможный пункт");
                }
            }
        }
    }
}
