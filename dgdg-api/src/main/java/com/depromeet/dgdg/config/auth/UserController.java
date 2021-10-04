package com.depromeet.dgdg.config.auth;

import com.depromeet.dgdg.common.ErrorCode;
import com.depromeet.dgdg.common.exception.NotFoundException;
import com.depromeet.dgdg.domain.domain.User.User;
import com.depromeet.dgdg.domain.domain.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequiredArgsConstructor
public class UserController {

    private UserRepository userRepository;

    @GetMapping("/auth/authorization/kakao")
    public User findUserById(String socialId) {
        User user = userRepository.findUserById(socialId);
        if (user == null) {
            throw new NotFoundException(String.format("존재하지 않는 아이디입니다."), ErrorCode.NOT_FOUND_EXCEPTION);
        }
        return user;
    }

}
