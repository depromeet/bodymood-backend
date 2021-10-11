package com.depromeet.dgdg.domain.domain.user.repository;

import com.depromeet.dgdg.domain.domain.user.SocialProvider;
import com.depromeet.dgdg.domain.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findUserBySocialIdAndSocialProvider(String socialId, SocialProvider provider);
}
