package com.depromeet.dgdg.domain.domain.user.repository;

import com.depromeet.dgdg.domain.domain.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.depromeet.dgdg.domain.domain.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public User findUserById(Long userId) {
        return queryFactory.selectFrom(user)
            .where(
                user.id.eq(userId)
            ).fetchOne();
    }

    @Override
    public User findByRefreshToken(@NotNull String refreshToken) {
        return queryFactory.selectFrom(user)
            .where(
                user.refreshToken.eq(refreshToken)
            ).fetchOne();
    }

}
