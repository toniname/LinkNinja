package com.applink.app.update;

import com.applink.app.configuration.IT;
import com.applink.app.configuration.IntegrationTestsDatabase;
import com.applink.app.database.entity.UrlEntity;
import com.applink.app.database.entity.UserEntity;
import com.applink.app.database.exception.UrlNotFoundException;
import com.applink.app.database.service.UrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@IT
@DirtiesContext
public class UpdateServiceTest extends IntegrationTestsDatabase {

    @Mock
    private UrlService urlService;

    @InjectMocks
    private UpdateService updateService;

    private UrlEntity urlEntity;

    @BeforeEach
    public void setUp() {
        urlEntity = UrlEntity.builder()
                .id(1L)
                .shortUrl("short-url")
                .longUrl("https://www.example.com")
                .visited(0)
                .user(UserEntity.builder().username("test-user").build())
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusDays(1))
                .build();
    }

    @Test
    public void testUpdate_validRequest_returnsOkResponse() throws UrlNotFoundException {
        when(urlService.findUrlEntityByShortUrl(eq("short-url"))).thenReturn(urlEntity);
        when(urlService.updateUrlEntity(any())).thenReturn(urlEntity);

        UpdateRequest updateRequest = UpdateRequest.builder()
                .shortUrl("short-url")
                .longUrl("https://www.new-example.com")
                .build();

        UpdateResponse response = updateService.update(updateRequest, "test-user");

        assertEquals(UpdateResponse.UpdateError.success, response.getUpdateError());
        assertEquals("https://www.new-example.com", response.getUrlDto().getLongUrl());
        verify(urlService).findUrlEntityByShortUrl(eq("short-url"));
        verify(urlService).updateUrlEntity(any());
    }

    @Test
    public void testUpdate_urlNotFound_throwsUrlNotFoundException() {
        when(urlService.findUrlEntityByShortUrl(eq("short-url"))).thenThrow(UrlNotFoundException.class);

        UpdateRequest updateRequest = UpdateRequest.builder()
                .shortUrl("short-url")
                .longUrl("https://www.new-example.com")
                .build();

        UrlNotFoundException exception = assertThrows(UrlNotFoundException.class, () -> updateService.update(updateRequest, "test-user"));
        assertEquals("Url not found", exception.getMessage());
        verify(urlService).findUrlEntityByShortUrl(eq("short-url"));
    }

    @Test
    public void testUpdate_notOwnedByUser_returnsErrorResponse() throws UrlNotFoundException {
        urlEntity.getUser().setUsername("different-user");
        when(urlService.findUrlEntityByShortUrl(eq("short-url"))).thenReturn(urlEntity);

        UpdateRequest updateRequest = UpdateRequest.builder()
                .shortUrl("short-url")
                .longUrl("https://www.new-example.com")
                .build();

        UpdateResponse response = updateService.update(updateRequest, "test-user");

        assertEquals(UpdateResponse.UpdateError.belongsToAnotherUser, response.getUpdateError());
        assertEquals(null, response.getUrlDto());
    }

    @Test
    public void testUpdate_linkExpired_returnsErrorResponse() throws UrlNotFoundException {
        urlEntity.setExpiredAt(LocalDateTime.now().minusDays(1));
        when(urlService.findUrlEntityByShortUrl(eq("short-url"))).thenReturn(urlEntity);

        UpdateRequest updateRequest = UpdateRequest.builder()
                .shortUrl("short-url")
                .longUrl("https://www.new-example.com")
                .build();

        UpdateResponse response = updateService.update(updateRequest, "test-user");

        assertEquals(UpdateResponse.UpdateError.linkExpired, response.getUpdateError());
        assertEquals(null, response.getUrlDto());
    }
}