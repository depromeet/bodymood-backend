package com.depromeet.dgdg.domain.domain.user.repository;

import com.depromeet.dgdg.domain.domain.user.User;
import org.jetbrains.annotations.NotNull;

public interface UserRepositoryCustom {

    User findUserById(Long userId);

    User findByRefreshToken(@NotNull String refreshToken);

}
