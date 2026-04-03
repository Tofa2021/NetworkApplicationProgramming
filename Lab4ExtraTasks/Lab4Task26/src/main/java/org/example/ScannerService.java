package org.example;

import java.util.ArrayList;
import java.util.List;

public interface ScannerService {
    String scanString();

    default double scanDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanString());
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод");
            }
        }
    }

    default List<Double> scanDoubles() {
        System.out.println("Для прекращения ввода нажмите ENTER");
        List<Double> doubles = new ArrayList<>();
        while (true) {
            String input = scanString();
            if (input.isEmpty()) {
                return doubles;
            }

            try {
                doubles.add(Double.parseDouble(input));
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод");
            }
        }
    }
}
