package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите степень многочлена");
        int n = scanner.nextInt();

        Polynomial polynomialA = new Polynomial();
        fillPolynomial(polynomialA, n, scanner);
        System.out.println("Первый многочлен: " + polynomialA);

        Polynomial polynomialB = new Polynomial();
        fillPolynomial(polynomialB, n, scanner);
        System.out.println("Второй многочлен: " + polynomialB);

        polynomialA.add(polynomialB);

        System.out.println("\nРезультат: " + polynomialA);
    }

    private static void fillPolynomial(Polynomial polynomial, int n, Scanner scanner) {
        for (int i = n; i >= 0; i--) {
            System.out.println("Введите коэффициент при X^" + i);
            double coefficient = scanner.nextDouble();
            polynomial.addCoefficient(i, coefficient);
        }
    }
}
