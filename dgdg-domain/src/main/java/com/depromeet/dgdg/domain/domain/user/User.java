package com.depromeet.dgdg.domain.domain.user;

import com.depromeet.dgdg.domain.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Email email;

    private String introduction;

    @Column(nullable = false)
    private String socialId;

    private String profileUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialProvider socialProvider;

    private String refreshToken;

    private User(String name, String email, String introduction, String socialId, String profileUrl, SocialProvider socialProvider, String refreshToken) {
        this.name = name;
        this.email = email == null ? null : Email.of(email);
        this.introduction = introduction;
        this.socialId = socialId;
        this.profileUrl = profileUrl;
        this.socialProvider = socialProvider;
        this.refreshToken = refreshToken;
    }

    public static User newInstance(String name, String email, String introduction, String socialId, String profileUrl, SocialProvider socialProvider, String refreshToken) {
        return new User(name, email, introduction, socialId, profileUrl, socialProvider, refreshToken);
    }

}
