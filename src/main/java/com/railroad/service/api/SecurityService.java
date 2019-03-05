package com.railroad.service.api;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String userName, String password);
}
