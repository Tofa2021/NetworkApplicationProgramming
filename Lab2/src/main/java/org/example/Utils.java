package org.example;

import java.util.List;

import static org.example.ScannerManager.scanBorderInt;

public class Utils {
    public static <T extends Nameable> T select(List<T> elements) {
        if (elements.isEmpty()) {
            System.out.println("Пусто");
            return null;
        }

        for (int i = 0; i < elements.size(); i++) {
            System.out.println((i + 1) + ") " + elements.get(i).getName());
        }
        int selectedIndex = scanBorderInt(1, elements.size()) - 1;
        return elements.get(selectedIndex);
    }
}
