package org.example.service.security;

import org.example.service.scanner.ScannerService;

public class SecurityServiceImpl implements SecurityService {
    private final String PASSWORD = "1234";
    private final ScannerService scannerService;

    public SecurityServiceImpl(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @Override
    public boolean isPasswordCorrect(String password) {
        return password.equals(PASSWORD);
    }

    @Override
    public boolean checkPassword() {
        System.out.println("Введите пароль");
        String password = scannerService.scanNonEmptyString();
        return isPasswordCorrect(password);
    }
}
