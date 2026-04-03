package org.example;

import java.util.LinkedHashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ScannerService scannerService = new SystemInScannerService();

        System.out.println("Введите числа");
        List<Double> numbers = scannerService.scanDoubles();
        LinkedHashSet<Double> set = new LinkedHashSet<>(numbers);

        for (int i = 0; i < Math.log(numbers.size()) / Math.log(2); i++) {
            boolean isSizeOdd = set.size() % 2 == 1;
            int n = set.size() / 2;

            for (int j = 0; j < n; j++) {
                double a = set.removeFirst();
                double b = set.removeFirst();
                set.addLast(a + b);
            }

            if (isSizeOdd) {
                System.out.println("Relink");
                set.addLast(set.removeFirst());
            }

            System.out.println(set);
        }
    }
}
