package com.appLink.app.database.service;

import com.appLink.app.configuration.IT;
import com.appLink.app.configuration.IntegrationTestsDatabase;
import com.appLink.app.database.entity.UrlEntity;
import com.appLink.app.database.entity.UserEntity;
import com.appLink.app.database.exception.UrlNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.time.format.DateTimeFormatter;
import java.util.List;

@IT
@DirtiesContext
public class UrlServiceTest extends IntegrationTestsDatabase {
    @Autowired
    private UrlService urlService;
    @Autowired
    private UserService userService;

    @Test
    public void testThatDatabaseSearchWorks() {
        try {
            urlService.findUrlEntityByShortUrl("abc123");
        }catch (UrlNotFoundException e) {
            Assertions.fail("Url not found");
        }
    }

    @Test
    public void testThatFindUrlEntityByShortUrlThrowsException() {
        Assertions.assertThrows(UrlNotFoundException.class,
                () -> urlService.findUrlEntityByShortUrl("nonExisted"));
    }
    @Test
    public void testThatSaveEntityWorks() {
        UserEntity johnDoe = userService.getUserByUsername("john_doe");
        UrlEntity userEntity = UrlEntity.builder()
                .shortUrl("2d3i3c")
                .longUrl("https://google.com")
                .user(johnDoe)
                .build();

        UrlEntity urlEntity = urlService.saveUrlEntity(userEntity);

        Assertions.assertNotNull(urlEntity);
        Assertions.assertTrue(urlEntity.getId() > 0);
        Assertions.assertEquals(urlEntity.getShortUrl(), userEntity.getShortUrl());
        Assertions.assertEquals(urlEntity.getLongUrl(), userEntity.getLongUrl());
        Assertions.assertNotNull(urlEntity.getUser());
        Assertions.assertNotNull(urlEntity.getCreatedAt());
        Assertions.assertNotNull(urlEntity.getExpiredAt());
        Assertions.assertEquals(urlEntity.getExpiredAt().format(DateTimeFormatter.ISO_DATE),
                urlEntity.getCreatedAt().plusDays(14).format(DateTimeFormatter.ISO_DATE));
    }

    @Test
    public void testThatEntityUpdateWorks() {
        UrlEntity urlEntityByShortUrl = urlService.findUrlEntityByShortUrl("2d3i3c");
        urlEntityByShortUrl.setLongUrl("https://github.com");

        UrlEntity urlEntity = urlService.updateUrlEntity(urlEntityByShortUrl);

        Assertions.assertNotNull(urlEntityByShortUrl);
        Assertions.assertEquals(urlEntity.getId(), urlEntityByShortUrl.getId());
    }

    @Test
    public void testThatEntityDeleteWorks() {
        UrlEntity urlEntity = urlService.findUrlEntityByShortUrl("abc123");

        urlService.deleteUrlEntity(urlEntity);

        Assertions.assertThrows(UrlNotFoundException.class,
                () -> urlService.findUrlEntityByShortUrl("abc123"));
    }

    @Test
    public void testListAllUserUrlsByUsername() {
        List<UrlEntity> johnDoe = urlService.findAllUrlEntityByUsername("jane_smith");
        Assertions.assertNotNull(johnDoe);
        Assertions.assertEquals(johnDoe.size(), 1);
    }

    @Test
    public void testThatLongUrlIsFound() {
        String shortUrl = "ghi789";
        String longUrl = urlService.getLongUrl(shortUrl);
        Assertions.assertEquals(0, longUrl.compareTo("https://google.com"));
    }

}