package com.depromeet.dgdg.domain.domain.User.repository;

import com.depromeet.dgdg.domain.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstById(Long id);

}
