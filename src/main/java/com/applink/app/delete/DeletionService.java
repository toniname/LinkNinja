package com.applink.app.delete;


import com.applink.app.database.entity.UrlEntity;
import com.applink.app.database.entity.UserEntity;
import com.applink.app.database.exception.UrlNotFoundException;
import com.applink.app.database.service.UrlService;
import com.applink.app.delete.exceptions.DeletionNotAllowedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
@Slf4j
public class DeletionService {

    private final UrlService urlService;


    public DeleteResponse delete(DeleteRequest deleteRequest, String currentlyAuthenticatedUser)
            throws DeletionNotAllowedException {

        try {
            UrlEntity urlEntity = urlService.findUrlEntityByShortUrl(deleteRequest.getShortUrl());
            UserEntity owner = urlEntity.getUser();
            if (owner.getUsername().equals(currentlyAuthenticatedUser)) {
                urlService.deleteUrlEntity(urlEntity);
                return DeleteResponse.success();
            } else {
                throw new DeletionNotAllowedException("Url not owned by " + currentlyAuthenticatedUser);
            }
        } catch (UrlNotFoundException e) {
            log.error(e.getMessage());
            return DeleteResponse.notFound();
        } catch (DeletionNotAllowedException e) {
            log.error(e.getMessage());
            return DeleteResponse.notAllowed();
        }
    }


}
