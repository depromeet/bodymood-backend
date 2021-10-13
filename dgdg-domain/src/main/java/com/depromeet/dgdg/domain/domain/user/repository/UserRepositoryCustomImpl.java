package com.depromeet.dgdg.domain.domain.user.repository;

import com.depromeet.dgdg.domain.domain.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

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

}
