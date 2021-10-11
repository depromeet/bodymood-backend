package com.depromeet.dgdg.service.auth;

import com.depromeet.dgdg.domain.domain.user.SocialProvider;
import com.depromeet.dgdg.external.kakao.KakaoClient;
import com.depromeet.dgdg.external.kakao.dto.response.KakaoUserResponse;
import com.depromeet.dgdg.service.auth.dto.request.AuthRequest;
import com.depromeet.dgdg.domain.domain.user.User;
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoClient kakaoClient;
    private final UserRepository userRepository;

    @Transactional
    public Long handleAuthentication(AuthRequest request) {
        KakaoUserResponse userInfo = kakaoClient.getUserInfo(request.getAccessToken());
        User user = userRepository.findUserBySocialIdAndProvider(userInfo.getId(), SocialProvider.KAKAO);
        if (user == null) {
            return signUpUser(userInfo).getId();
        }
        return user.getId();
    }

    private User signUpUser(KakaoUserResponse userInfo) {
        User newUser = User.newKaKaoInstance(userInfo.getId(), userInfo.getNickName(), userInfo.getProfileImage());
        return userRepository.save(newUser);
    }

}
