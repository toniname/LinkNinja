package com.appLink.app.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "urls")
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "urls_sequence")
    @SequenceGenerator(name = "urls_sequence", sequenceName = "URLS_SEQ", allocationSize = 1)
    @Column(name = "id")
    private long id;
    @Column(name = "short_url")
    private String shortUrl;
    @Column(name = "long_url")
    private String longUrl;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEntity user;
    @Column(name = "visited")
    private long visited;
    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "expired_at")
    @Builder.Default
    private LocalDateTime expiredAt = LocalDateTime.now().plusDays(14);

}