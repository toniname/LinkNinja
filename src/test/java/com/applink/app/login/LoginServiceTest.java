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

        assertNotNull(login);
        assertNotNull(login.getToken());
        assertEquals(LoginResponse.LoginError.SUCCESS, login.getLoginError());
    }

    @Test
    public void testNotValidCredentials() {
        String username = "nonExistingUser";
        String password = "WrongPassword";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        LoginResponse login = loginService.login(loginRequest);

        assertNotNull(login);
        assertNull(login.getToken());
        assertEquals(LoginResponse.LoginError.BAD_CREDENTIALS, login.getLoginError());
    }

}