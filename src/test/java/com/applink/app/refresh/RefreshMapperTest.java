package com.applink.app.refresh;

import com.applink.app.configuration.IT;
import com.applink.app.configuration.IntegrationTestsDatabase;
import com.applink.app.database.entity.UrlEntity;
import com.applink.app.database.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@IT
class RefreshMapperTest extends IntegrationTestsDatabase {

    @Autowired
    private RefreshMapper refreshMapper;

    @Test
    public void testRefreshMapper() {
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

        RefreshResponse refreshResponse = refreshMapper.refreshResponse(urlEntity);

        assertEquals(0, refreshResponse.getOwnedBy().compareTo(user.getUsername()));
        assertEquals(0, refreshResponse.getShortUrl().compareTo("localhost:9999/urls/2jf92"));
        assertEquals(0, refreshResponse.getLongUrl().compareTo(urlEntity.getLongUrl()));
        assertEquals(0, refreshResponse.getVisited().compareTo(urlEntity.getVisited()));
        assertEquals(0, refreshResponse.getCreatedAt().compareTo(urlEntity.getCreatedAt()));
        assertEquals(0, refreshResponse.getExpiredAt().compareTo(urlEntity.getExpiredAt()));
    }
}
