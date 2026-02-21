package org.example;

public class AuthService {
    private final static String PASSWORD = "1234";

    public static boolean checkPassword(String password) {
        return password.equals(AuthService.PASSWORD);
    }
}
