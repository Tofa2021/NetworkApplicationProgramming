package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public interface ScannerService {
    String scanString();

    default int scanBorderInt(int minValue, int maxValue) {
        while (true) {
            int scannedValue = scanInt();
            if (minValue <= scannedValue && scannedValue <= maxValue) {
                return scannedValue;
            }
            System.out.println("Число выходит за границу диапазона. Повторите попытку");
        }
    }

    default LocalDate scanLocalDate() {
        while (true) {
            String dateString = scanString();
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат. Повторите попытку");
            }
        }
    }

    default int scanInt() {
        while (true) {
            try {
                return Integer.parseInt(scanString());
            } catch (InputMismatchException e) {
                System.out.println("Неправильной ввод. Повторите попытку");
            }
        }
    }
}
