package org.example;

import org.example.service.ScannerService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Utils {
    public static <T extends Nameable> T select(ScannerService scanner, List<T> elements) {
        if (elements.isEmpty()) {
            throw new NoSuchElementException();
        }

        printList(elements);
        int selectedIndex = scanner.scanBorderInt(1, elements.size()) - 1;
        return elements.get(selectedIndex);
    }

    public static <T extends Nameable> void printList(List<T> elements) {
        if (elements.isEmpty()) {
            System.out.println("Пусто");
        }

        for (int i = 0; i < elements.size(); i++) {
            System.out.println((i + 1) + ") " + elements.get(i).getName());
        }
    }

    public static <T extends Nameable> List<T> multiSelect(ScannerService scanner, List<T> elements, int count) {
        if (elements.isEmpty()) {
            throw new NoSuchElementException();
        }

        List<T> copiedElements = new ArrayList<>(elements);
        List<T> selectedElements = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            T element = select(scanner, copiedElements);
            copiedElements.remove(element);
            selectedElements.add(element);
        }

        return selectedElements;
    }
}
