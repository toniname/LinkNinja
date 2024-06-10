package com.appLink.app.registration;


import com.appLink.app.configuration.IT;
import com.appLink.app.configuration.IntegrationTestsDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@IT
@DirtiesContext
public class RegistrationServiceTest extends IntegrationTestsDatabase {
    @Autowired
    private RegistrationService registrationService;

    private RegistrationRequest registrationRequest;

    @BeforeEach
    public void setUp() {
        String validPassword = "PassworD12345";
        registrationRequest = new RegistrationRequest();
        registrationRequest.setUsername("RegistrationServiceTestUser");
        registrationRequest.setPassword(validPassword);

    }

    @Test
    public void testThatRegistrationServiceWorks() {
        RegistrationResponse registrationResponse = registrationService.register(registrationRequest);
        assertNotNull(registrationResponse);
        assertEquals(registrationResponse.getError(), RegistrationResponse.RegistrationError.SUCCESS);
        assertNotNull(registrationResponse.getToken());
    }
}