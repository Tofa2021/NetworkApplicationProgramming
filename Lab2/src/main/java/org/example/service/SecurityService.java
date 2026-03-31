package org.example.service;

public interface SecurityService {
    boolean checkPassword(String password);

    void handleSecuredAction(Runnable runnable);
}
