package com.applink.app.database.service;

import com.applink.app.configuration.IT;
import com.applink.app.configuration.IntegrationTestsDatabase;
import com.applink.app.database.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@DirtiesContext()
public class UserServiceTest extends IntegrationTestsDatabase {
    @Autowired
    private UserService userService;

    @Test
    public void testThatUserIsFoundInDatabase() {
        try {
            UserEntity johnDoe = userService.getUserByUsername("john_doe");
            Assertions.assertNotNull(johnDoe);
        } catch (UsernameNotFoundException e) {
            Assertions.fail("User not found");
        }
    }

    @Test
    public void testThatUserIsNotFoundInDatabase() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> {userService.getUserByUsername("username");});
    }


}