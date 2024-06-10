package com.applink.app.login;

import com.applink.app.configuration.IT;
import com.applink.app.configuration.IntegrationTestsDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IT
class LoginServiceTest extends IntegrationTestsDatabase {
    @Autowired
    private LoginService loginService;


    @Test
    public void testThatLoginServiceReturnToken() {
        String username = "john_doe";
        String password = "password";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        LoginResponse login = loginService.login(loginRequest);

        assertTrue(login.getToken() != "null");
    }

    @Test
    public void testNotValidCredentials() {
        String username = "nonExistingUser";
        String password = "WrongPassword";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        LoginResponse login = loginService.login(loginRequest);

        assertFalse(login.getToken() != "null");
    }

}