package com.appLink.app.database.repository;


import com.appLink.app.database.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    Optional<UrlEntity> findUrlEntitiesByShortUrl(String shortUrl);

    List<UrlEntity> findAllByUserUsername(String username);

}