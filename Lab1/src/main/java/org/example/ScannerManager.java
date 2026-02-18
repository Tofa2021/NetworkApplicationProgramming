package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Supplier;

public class ScannerManager {
    public static final Scanner scanner = new Scanner(System.in);

    public static int scanInt() {
        return scan(scanner::nextInt);
    }

    public static String scanString() {
        return scan(scanner::next);
    }

    public static LocalDate scanLocalDate() {
        while (true) {
            String dateString = scanString();
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат. Повторите попытку");
            }
        }
    }

    private static <E> E scan(Supplier<E> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (InputMismatchException e) {
                System.out.println("Неправильной ввод. Повторите попытку");
            }
        }
    }
}
