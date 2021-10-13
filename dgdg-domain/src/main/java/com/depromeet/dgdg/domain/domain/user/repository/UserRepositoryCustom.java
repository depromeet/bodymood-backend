package com.depromeet.dgdg.domain.domain.user.repository;

import com.depromeet.dgdg.domain.domain.user.User;

public interface UserRepositoryCustom {

    User findUserById(Long userId);

}
