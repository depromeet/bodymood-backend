package com.depromeet.dgdg.domain.domain.user.repository;

import com.depromeet.dgdg.domain.domain.user.SocialProvider;
import com.depromeet.dgdg.domain.domain.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.depromeet.dgdg.domain.domain.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public User findUserBySocialIdAndProvider(String socialId, SocialProvider provider) {
        return queryFactory.selectFrom(user)
            .where(
                user.socialId.eq(socialId),
                user.socialProvider.eq(provider)
            ).fetchOne();
    }

}
