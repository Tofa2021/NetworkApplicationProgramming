package org.example;

import java.util.Scanner;

public class SystemInScannerService implements ScannerService {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String scanString() {
        return scanner.nextLine();
    }
}
