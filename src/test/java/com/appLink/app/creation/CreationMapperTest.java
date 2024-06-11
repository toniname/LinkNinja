package com.appLink.app.creation;

import com.appLink.app.configuration.IT;
import com.appLink.app.configuration.IntegrationTestsDatabase;
import com.appLink.app.database.entity.UrlEntity;
import com.appLink.app.database.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@IT
public class CreationMapperTest extends IntegrationTestsDatabase {

    @Autowired
    private CreationMapper creationMapper;


    @Test
    public void testCreationMapper() {
        UserEntity user = UserEntity.builder()
                .id(1L)
                .username("john")
                .password("password")
                .build();
        UrlEntity urlEntity = UrlEntity.builder()
                .id(1)
                .shortUrl("2jf92")
                .longUrl("https://google.com")
                .visited(0)
                .user(user)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusDays(14))
                .build();

        CreationResponse creationResponse = creationMapper.creationResponse(urlEntity);

        assertEquals(0, creationResponse.getOwnedBy().compareTo(user.getUsername()));
        assertEquals(0, creationResponse.getShortUrl().compareTo("localhost:9999/urls/2jf92"));
    }
}