package org.example;

import java.util.Scanner;

public class SystemInService implements ScannerService {
    public static final SystemInService INSTANCE = new SystemInService();
    private static final Scanner scanner = new Scanner(System.in);

    private SystemInService() {
    }

    @Override
    public String scanString() {
        return scanner.nextLine();
    }
}
