package com.applink.app.database.service;

import com.applink.app.configuration.IntegrationTestsDatabase;
import com.applink.app.database.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;


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

    @Test
    public void testThatUserFoundById() {
        try {
            UserEntity userById = userService.getUserById(1L);
            Assertions.assertEquals(0, userById.getUsername().compareTo("john_doe"));
        } catch (UsernameNotFoundException e) {
            Assertions.fail("User not found");
        }
    }

    @Test
    public void testThatUserSaved() {
        UserEntity user = UserEntity.builder()
                .username("newUser")
                .password("Password22")
                .build();
        UserEntity userEntity = userService.saveUser(user);

        Assertions.assertNotNull(userEntity);
        Assertions.assertEquals("newUser", userEntity.getUsername());
        Assertions.assertTrue(userEntity.getId() > 0);
        Assertions.assertTrue(userEntity.isEnabled());
    }
}