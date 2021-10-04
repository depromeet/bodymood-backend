package com.depromeet.dgdg.domain.domain.User.repository;

import com.depromeet.dgdg.domain.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(String socialId);
}
