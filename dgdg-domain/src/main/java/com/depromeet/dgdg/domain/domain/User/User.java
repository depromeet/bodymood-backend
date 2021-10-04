package com.depromeet.dgdg.domain.domain.User;

import com.depromeet.dgdg.domain.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.xml.bind.ValidationException;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User extends BaseTimeEntity {

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
