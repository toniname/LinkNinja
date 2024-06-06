package com.applink.app.database.repository;

import com.applink.app.database.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    @Query("FROM UrlEntity n WHERE ne.shortUrl = :shortUrl")
    Optional<UrlEntity> findUrlEntitiesByShortUrl(@Param("shortUrl") String shortUrl);

    @Query("FROM UrlEntity n WHERE n.user.username = :username")
    List<UrlEntity> findAllByUserUsername(@Param("username") String username);

}
