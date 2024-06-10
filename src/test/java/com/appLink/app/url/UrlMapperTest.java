package com.appLink.app.url;


import com.appLink.app.configuration.IT;
import com.appLink.app.configuration.IntegrationTestsDatabase;
import com.appLink.app.database.dto.UrlDto;
import com.appLink.app.database.entity.UrlEntity;
import com.appLink.app.database.service.UrlService;
import com.appLink.app.url.UrlMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@IT
class UrlMapperTest extends IntegrationTestsDatabase {
    @Autowired
    private UrlMapper urlMapper;
    @Autowired
    private UrlService urlService;

    @Test
    public void testUrlMapperMapEntities() {
        List<UrlEntity> allUrlEntityByUsername = urlService.findAllUrlEntityByUsername("david_miller");

        List<UrlDto> urlDtos = urlMapper.mapToUrlDtoList(allUrlEntityByUsername);

        assertEquals(allUrlEntityByUsername.size(), urlDtos.size());
        assertEquals(0, allUrlEntityByUsername.getFirst().getLongUrl()
                .compareTo(urlDtos.getFirst().getLongUrl()));
    }
}