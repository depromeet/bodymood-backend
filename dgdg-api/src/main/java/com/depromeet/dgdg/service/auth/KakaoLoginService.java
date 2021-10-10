package com.depromeet.dgdg.service.auth;

import com.depromeet.dgdg.common.ErrorCode;
import com.depromeet.dgdg.common.exception.NotFoundException;
import com.depromeet.dgdg.config.kakaoLogin.KakaoClient;
import com.depromeet.dgdg.config.kakaoLogin.KakaoUserResponse;
import com.depromeet.dgdg.config.kakaoLogin.LoginRequest;
import com.depromeet.dgdg.domain.domain.user.User;
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoClient kakaoClient;
    private final UserRepository userRepository;

    public Long login(LoginRequest request) {
        KakaoUserResponse userInfo = kakaoClient.getUserInfo(request.getAccessToken());
        User user = userRepository.findFirstById(userInfo.getId())
            .orElseThrow(() -> new NotFoundException(String.format("없는 유저 (%s) 입니다.", userInfo.getId()), ErrorCode.NOT_FOUND_EXCEPTION));
        // TODO 없는 유저면 회원가입 진행 로직 작성하기
        return user.getId();
    }

}
