package com.depromeet.dgdg.domain.domain.User;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "userProfile")
public class UserProfile {

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime lastModifiedDateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, length = 50)
    private String photoId;

    @JoinColumn(name = "email")
    private Long userId;

    public UserProfile(String type, String photoId, Long userId) {
        this.type = type;
        this.photoId = photoId;
        this.userId = userId;
    }

    public UserProfile() {

    }
}
