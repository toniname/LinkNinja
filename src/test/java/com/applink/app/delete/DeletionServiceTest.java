package com.applink.app.delete;

import static org.junit.jupiter.api.Assertions.*;
import com.applink.app.configuration.IT;
import com.applink.app.configuration.IntegrationTestsDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


@IT
class DeletionServiceTest extends IntegrationTestsDatabase{

    @Autowired
    private DeletionService deletionService;


    @Test
    public void testNotAllowed() {
        String username = "jane_smith";
        String shortUrl = "yzu567";

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setShortUrl(shortUrl);

        DeleteResponse deleteResponse = deletionService.delete(deleteRequest, username);

        assertEquals(DeleteResponceMessage.NOT_ALLOWED.getStatus(), deleteResponse.getStatus());
        assertEquals(DeleteResponceMessage.NOT_ALLOWED.getMesaage(), deleteResponse.getMessage());
    }

    @Test
    public void testUrlNotExist() {
        String username = "john_doe";
        String shortUrl = "xxxxxxxx";

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setShortUrl(shortUrl);

        DeleteResponse deleteResponse = deletionService.delete(deleteRequest, username);

        assertEquals(DeleteResponceMessage.NOT_FOUND.getStatus(), deleteResponse.getStatus());
        assertEquals(DeleteResponceMessage.NOT_FOUND.getMesaage(), deleteResponse.getMessage());
    }

    @Test
    public void testSuccessDeletion() {
        String username = "john_doe";
        String shortUrl = "abc123";

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setShortUrl(shortUrl);

        DeleteResponse deleteResponse = deletionService.delete(deleteRequest, username);

        assertEquals(DeleteResponceMessage.SUCCESS.getStatus(), deleteResponse.getStatus());
        assertEquals(DeleteResponceMessage.SUCCESS.getMesaage(), deleteResponse.getMessage());
    }

}