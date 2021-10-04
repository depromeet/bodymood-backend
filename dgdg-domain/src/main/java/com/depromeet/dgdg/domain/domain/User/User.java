package com.depromeet.dgdg.domain.domain.User;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User {

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime lastModifiedDateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    private Email email;

    @Column(nullable = false, length = 50)
    private String introduction;

    @Column(nullable = false, length = 50)
    private String socialId;

    @Column(nullable = false, length = 50)
    private String socialProvider;

    @Column(nullable = false, length = 50)
    private String refreshToken;

    public User(String name, String email, String introduction, String socialId, String socialProvider, String refreshToken) throws ValidationException {
        this.name = name;
        this.email = email == null ? null : Email.of(email);
        this.introduction = introduction;
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.refreshToken = refreshToken;
    }

    public User() {

    }

    public static User newInstance(String name, String email, String introduction, String socialId, String socialProvider, String refreshToken) throws ValidationException {
        return new User(name, email, introduction, socialId, socialProvider, refreshToken);
    }
}
