package org.example;

import java.util.Scanner;

public class SystemInService implements ScannerService {
    public static final SystemInService INSTANCE = new SystemInService();
    public static final Scanner scanner = new Scanner(System.in);

    public SystemInService() {
    }

    @Override
    public String scanString() {
        return scanner.nextLine();
    }
}
