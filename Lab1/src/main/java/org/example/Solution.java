package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Solution {
    private static final Logger logger = LogManager.getLogger(Solution.class);

    public static void solve(ScannerService scanner) {
        StudentGroup group = new StudentGroup();

        mainLoop:
        while (true) {
            System.out.println("Выберите действие");
            System.out.println("1) Добавить студента");
            System.out.println("2) Добавить пропуск");
            System.out.println("3) Просмотреть пропуски группы");
            System.out.println("0) Выход");

            switch (scanner.scanInt()) {
                case 0 -> {
                    break mainLoop;
                }
                case 1 -> {
                    System.out.println("Введите фамилию студента");
                    String surname = scanner.scanString();
                    group.addStudent(new Student(surname));
                }
                case 2 -> {
                    if (group.getStudents().isEmpty()) {
                        System.out.println("Еще нет студентов");
                        break;
                    }

                    System.out.println("Выберите студента");
                    group.showGroupList();
                    int studentIndex = scanner.scanInt() - 1;
                    Student student = group.getStudents().get(studentIndex);

                    System.out.println("Введите дату пропуска");
                    student.addAbsence(scanner.scanLocalDate());
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
