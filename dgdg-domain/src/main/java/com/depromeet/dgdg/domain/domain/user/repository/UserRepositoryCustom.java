package com.depromeet.dgdg.domain.domain.user.repository;

import com.depromeet.dgdg.domain.domain.user.SocialProvider;
import com.depromeet.dgdg.domain.domain.user.User;
import org.jetbrains.annotations.NotNull;

public interface UserRepositoryCustom {

    User findActiveUsersById(Long userId);

    User findActiveUserBySocialIdAndSocialProvider(String socialId, SocialProvider provider);

    User findByRefreshToken(@NotNull String refreshToken);

    User findUserByIdFetchJoinPoster(Long userId);
}
