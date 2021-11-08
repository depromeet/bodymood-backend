package com.depromeet.dgdg.domain.domain.user.repository;

import com.depromeet.dgdg.domain.domain.user.SocialProvider;
import com.depromeet.dgdg.domain.domain.user.User;
import com.depromeet.dgdg.domain.domain.user.UserStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.depromeet.dgdg.domain.domain.poster.QPoster.poster;
import static com.depromeet.dgdg.domain.domain.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public User findActiveUsersById(Long userId) {
        return queryFactory.selectFrom(user)
            .where(
                user.id.eq(userId),
                user.status.eq(UserStatus.ACTIVE)
            ).fetchOne();
    }

    @Override
    public User findActiveUserBySocialIdAndSocialProvider(String socialId, SocialProvider provider) {
        return queryFactory.selectFrom(user)
            .where(
                user.socialId.eq(socialId),
                user.socialProvider.eq(provider),
                user.status.eq(UserStatus.ACTIVE)
            )
            .fetchOne();
    }

    @Override
    public User findByRefreshToken(@NotNull String refreshToken) {
        return queryFactory.selectFrom(user)
            .where(
                user.refreshToken.eq(refreshToken),
                user.status.eq(UserStatus.ACTIVE)
            ).fetchOne();
    }

    @Override
    public User findActiveUserByIdFetchJoinPoster(Long userId) {
        return queryFactory.selectFrom(user)
            .leftJoin(user.posters, poster)
            .fetchJoin()
            .where(
                user.id.eq(userId),
                user.status.eq(UserStatus.ACTIVE)
            )
            .fetchOne();
    }
}
