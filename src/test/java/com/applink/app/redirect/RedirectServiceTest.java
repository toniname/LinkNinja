package com.applink.app.redirect;

import com.applink.app.configuration.IT;
import com.applink.app.configuration.IntegrationTestsDatabase;
import com.applink.app.database.entity.UrlEntity;
import com.applink.app.database.service.UrlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IT
class RedirectServiceTest extends IntegrationTestsDatabase {
    @Autowired
    private RedirectService redirectService;
    @Autowired
    private UrlService urlService;

    @Test
    public void testThatReturnLongUrlAndUpdateVisited() {
        UrlEntity urlEntityByShortUrl = urlService.findUrlEntityByShortUrl("def456");
        long lowerValue = urlEntityByShortUrl.getVisited();

        String s = redirectService.redirectString("def456");

        UrlEntity urlEntityByShortUrl1 = urlService.findUrlEntityByShortUrl("def456");
        long higherValue = urlEntityByShortUrl1.getVisited();

        assertEquals(0, s.compareTo("https://google.com"));
        Assertions.assertTrue(lowerValue < higherValue);
    }


}