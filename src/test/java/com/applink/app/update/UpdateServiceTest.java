package com.applink.app.update;

import com.applink.app.configuration.IT;
import com.applink.app.configuration.IntegrationTestsDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
class UpdateServiceTest extends IntegrationTestsDatabase {
    @Autowired
    private UpdateService updateService;

    @Test
    public void testThatUpdateMethodWorks() {
        // Arrange
        String username = "john_doe";
        String shortUrl = "abc123";
        String longUrl = "https://github.com";
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setShortUrl(shortUrl);
        updateRequest.setLongUrl(longUrl);

        // Act
        UpdateResponse update = updateService.update(updateRequest, username);

        // Assert
        assertEquals(UpdateResponse.UpdateError.SUCCESS, update.getUpdateError());
        assertNotNull(update);
        assertEquals(longUrl, update.getUrlDto().getLongUrl());
    }
}