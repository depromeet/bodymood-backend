package com.depromeet.dgdg.service.auth;

import com.depromeet.dgdg.provider.token.JwtAuthTokenProvider;
import com.depromeet.dgdg.service.auth.dto.response.AuthResponse;
import com.depromeet.dgdg.domain.domain.user.SocialProvider;
import com.depromeet.dgdg.external.kakao.KakaoClient;
import com.depromeet.dgdg.external.kakao.dto.response.KakaoUserResponse;
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload;
import com.depromeet.dgdg.domain.domain.user.User;
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository;
import com.depromeet.dgdg.service.auth.dto.request.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoClient kakaoClient;
    private final UserRepository userRepository;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @Transactional
    public AuthResponse handleAuthentication(AuthRequest request) {
        KakaoUserResponse userInfo = kakaoClient.getUserInfo(request.getAccessToken());
        User user = userRepository.findActiveUserBySocialIdAndSocialProvider(userInfo.getId(), SocialProvider.KAKAO);
        if(user == null){
            user = signUpUser(userInfo);
        }
        String accessToken = jwtAuthTokenProvider.createAccessToken(AuthTokenPayload.of(user.getId()));
        String refreshToken = jwtAuthTokenProvider.createRefreshToken();
        user.updateRefreshToken(refreshToken);
        return AuthResponse.of(accessToken, refreshToken);
    }

    private User signUpUser(KakaoUserResponse userInfo) {
        User newUser = User.newKaKaoInstance(userInfo.getId(), userInfo.getNickName(), userInfo.getProfileImage());
        return userRepository.save(newUser);
    }

}
