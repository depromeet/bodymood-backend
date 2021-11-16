package com.depromeet.dgdg.domain.domain.user;

import com.depromeet.dgdg.common.RandomGenerator;
import com.depromeet.dgdg.domain.domain.BaseTimeEntity;
import com.depromeet.dgdg.domain.domain.poster.Poster;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "member",
    uniqueConstraints = {
        @UniqueConstraint(name = "uni_member_1", columnNames = {"socialId", "socialProvider"})
    },
    indexes = {
        @Index(name = "idx_member_1", columnList = "refreshToken")
    }
)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String socialId;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private SocialProvider socialProvider;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(length = 2048)
    private String profileUrl;

    private String refreshToken;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Poster> posters = new ArrayList<>();

    protected User() {
    }

    User(String socialId, SocialProvider socialProvider, String name, String profileUrl) {
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.name = name;
        this.profileUrl = profileUrl;
    }

    public static User newKaKaoInstance(String socialId, String name, String profileUrl) {
        return new User(socialId, SocialProvider.KAKAO, name, profileUrl);
    }

    public static User newAppleInstance(String socialId) {
        return new User(socialId, SocialProvider.APPLE, RandomGenerator.getDefaultName(), null);
    }

    public void updateInfo(@NotNull String name, String profileUrl) {
        this.name = name;
        this.profileUrl = profileUrl;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void removeRefreshToken() {
        this.refreshToken = null;
    }

    public void delete() {
        this.status = UserStatus.DELETED;
    }

    public Long getId() {
        return id;
    }

    public String getSocialId() {
        return socialId;
    }

    public SocialProvider getSocialProvider() {
        return socialProvider;
    }

    public String getName() {
        return name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public List<Poster> getPosters() {
        return posters;
    }

}
