package com.depromeet.dgdg.config.kakaoLogin;

import com.depromeet.dgdg.common.ErrorCode;
import com.depromeet.dgdg.common.exception.NotFoundException;
import com.depromeet.dgdg.domain.domain.User.User;
import com.depromeet.dgdg.domain.domain.User.repository.UserRepository;
import com.depromeet.dgdg.provider.token.JwtAuthTokenProvider;
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoClient kakaoClient;
    private final UserRepository userRepository;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    public String login(String accessToken) {
        KakaoUserResponse userInfo = kakaoClient.getUserInfo(accessToken);
        Optional<User> user = userRepository.findFirstBySocialId(userInfo.getId());
        if (!user.isPresent()) {
            throw new NotFoundException("없는 유저입니다.", ErrorCode.NOT_FOUND_EXCEPTION);
        }
        return jwtAuthTokenProvider.createAccessToken(AuthTokenPayload.Companion.of(user.get().getId()));

    }


}
