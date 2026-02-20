package org.example;

public class AuthService {
    private final static String password = "1234";

    public static boolean checkPassword(String password) {
        return password.equals(AuthService.password);
    }
}
