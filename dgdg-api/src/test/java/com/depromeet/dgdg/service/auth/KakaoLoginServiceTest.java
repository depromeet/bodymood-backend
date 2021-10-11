package com.depromeet.dgdg.service.auth;

import com.depromeet.dgdg.domain.domain.user.SocialProvider;
import com.depromeet.dgdg.domain.domain.user.User;
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository;
import com.depromeet.dgdg.external.kakao.KakaoClient;
import com.depromeet.dgdg.external.kakao.dto.response.KakaoUserResponse;
import com.depromeet.dgdg.service.auth.dto.request.AuthRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KakaoLoginServiceTest {

    private static final String id = "KAKAO-id";
    private static final String nickName = "nickName";
    private static final String profileImage = "profileImage";

    private KakaoLoginService kakaoLoginService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        kakaoLoginService = new KakaoLoginService(new StubKakaoClient(), userRepository);
    }

    @AfterEach
    void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    void 회원이_아닌경우_회원가입이_진행되서_새로운_유저가_DB에_저장된다() {
        // when
        kakaoLoginService.handleAuthentication(AuthRequest.testInstance("accessToken"));

        // then
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getSocialId()).isEqualTo(id);
        assertThat(users.get(0).getSocialProvider()).isEqualTo(SocialProvider.KAKAO);
        assertThat(users.get(0).getName()).isEqualTo(nickName);
        assertThat(users.get(0).getProfileUrl()).isEqualTo(profileImage);
    }

    // TODO 로그인인 경우 테스트 코드 짜보시죵!

    private static class StubKakaoClient implements KakaoClient {

        @Override
        public KakaoUserResponse getUserInfo(String accessToken) {
            return new KakaoUserResponse(id, new KakaoUserResponse.Properties(nickName, profileImage));
        }

    }

}
