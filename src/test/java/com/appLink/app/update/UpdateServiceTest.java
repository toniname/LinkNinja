package com.appLink.app.update;


import com.appLink.app.configuration.IT;
import com.appLink.app.configuration.IntegrationTestsDatabase;
import com.appLink.app.update.UpdateRequest;
import com.appLink.app.update.UpdateResponse;
import com.appLink.app.update.UpdateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IT
class UpdateServiceTest extends IntegrationTestsDatabase {
    @Autowired
    private UpdateService updateService;


    @Test
    public void testThatUpdateMethodWorks() {
        String username = "john_doe";
        String shortUrl = "abc123";
        String longUrl = "https://github.com";
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setShortUrl(shortUrl);
        updateRequest.setLongUrl(longUrl);

        UpdateResponse update = updateService.update(updateRequest, username);
        assertEquals(0, update.getUpdateError().compareTo(UpdateResponse.UpdateError.SUCCESS));
        assertNotNull(update);
        assertEquals(0, update.getUrlDto().getLongUrl().compareTo(longUrl));
    }
}