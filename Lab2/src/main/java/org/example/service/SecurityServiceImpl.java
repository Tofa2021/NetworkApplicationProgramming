package org.example.service;

public class SecurityServiceImpl implements SecurityService {
    private final String PASSWORD = "1234";
    private final ScannerService scannerService;

    public SecurityServiceImpl(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @Override
    public boolean checkPassword(String password) {
        return password.equals(PASSWORD);
    }

    @Override
    public void handleSecuredAction(Runnable runnable) {
        System.out.println("Введите пароль");
        String password = scannerService.scanString();
        if (checkPassword(password)) {
            runnable.run();
        } else {
            System.out.println("Неправильный пароль");
        }
    }
}
