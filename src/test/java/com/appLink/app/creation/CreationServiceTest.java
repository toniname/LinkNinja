package com.appLink.app.creation;


import com.appLink.app.configuration.IT;
import com.appLink.app.configuration.IntegrationTestsDatabase;
import com.appLink.app.creation.CreationRequest;
import com.appLink.app.creation.CreationResponse;
import com.appLink.app.creation.CreationService;
import com.appLink.app.database.entity.UserEntity;
import com.appLink.app.database.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IT
class CreationServiceTest extends IntegrationTestsDatabase {
    @Autowired
    private CreationService creationService;
    @Autowired
    private UserService userService;

    @Test
    public void testCreateMethodWithValidData() {

        CreationRequest creationRequest = new CreationRequest();
        creationRequest.setLongUrl("google.com");

        UserEntity user = userService.getUserByUsername("john_doe");

        CreationResponse creationResponse = creationService.create(creationRequest, user.getUsername());

        assertNotNull(creationResponse);
        assertNotNull(creationResponse.getId());
        assertNotNull(creationResponse.getLongUrl());
        assertNotNull(creationResponse.getCreatedAt());
        assertNotNull(creationResponse.getExpiredAt());
        assertTrue(creationResponse.getOwnedBy().compareTo(user.getUsername())==0);

    }
}