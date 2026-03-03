package org.example;

import org.example.model.Describable;
import org.example.model.Nameable;
import org.example.service.ScannerService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

public class Utils {
    public static <T extends Nameable> void printList(List<T> elements) {
        if (elements.isEmpty()) {
            System.out.println("Пусто");
        }

        for (int i = 0; i < elements.size(); i++) {
            System.out.println((i + 1) + ") " + elements.get(i).getName());
        }
    }

    public static <T extends Nameable & Describable> void printDescribableList(List<T> elements) {
        if (elements.isEmpty()) {
            System.out.println("Пусто");
        }

        for (int i = 0; i < elements.size(); i++) {
            T element = elements.get(i);
            System.out.println((i + 1) + ") " + element.getName() + " " + element.getDescription());
        }
    }

    public static <T extends Nameable> T select(ScannerService scanner, List<T> elements) {
        if (elements.isEmpty()) {
            throw new NoSuchElementException();
        }

        printList(elements);
        int selectedIndex = scanner.scanBorderInt(1, elements.size()) - 1;
        return elements.get(selectedIndex);
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

    public static <T extends Nameable> List<T> multiSelect(ScannerService scanner, List<T> elements) {
        if (elements.isEmpty()) {
            throw new NoSuchElementException();
        }

        System.out.println("Для завершения выбора нажмите ENTER");

        List<T> copiedElements = new ArrayList<>(elements);
        List<T> selectedElements = new ArrayList<>();

        while (!copiedElements.isEmpty()) {
            printList(copiedElements);
            String input = scanner.scanString();
            if (input.isEmpty()) {
                break;
            }

            try {
                int index = Integer.parseInt(input) - 1;
                if (index < 0 || index >= copiedElements.size()) {
                    System.out.println("Число выходит за границу диапазона. Повторите попытку");
                    continue;
                }

                T element = copiedElements.get(index);
                copiedElements.remove(index);
                selectedElements.add(element);
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Неправильной ввод. Повторите попытку");
            }
        }

        return selectedElements;
    }
}
