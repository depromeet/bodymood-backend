package com.depromeet.dgdg.domain.domain.user;

import com.depromeet.dgdg.common.RandomGenerator;
import com.depromeet.dgdg.domain.domain.BaseTimeEntity;
import com.depromeet.dgdg.domain.domain.poster.Poster;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Poster> posters = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    User(String socialId, SocialProvider socialProvider, String name, String profileUrl) {
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.name = name;
        this.profileUrl = profileUrl;
    }

    public static User newKaKaoInstance(String socialId, String name, String profileUrl) {
        return User.builder()
            .socialId(socialId)
            .socialProvider(SocialProvider.KAKAO)
            .name(name)
            .profileUrl(profileUrl)
            .build();
    }

    public static User newAppleInstance(String socialId) {
        return User.builder()
            .socialId(socialId)
            .socialProvider(SocialProvider.APPLE)
            .name(RandomGenerator.getDefaultName())
            .build();
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

    public void updatePosters(Poster poster) {
        this.posters.add(poster);
        poster.setUser(this);
    }
}
