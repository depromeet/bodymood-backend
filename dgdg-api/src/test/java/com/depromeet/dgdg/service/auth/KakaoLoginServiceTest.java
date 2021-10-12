package com.depromeet.dgdg.service.auth;

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

    private static final String id = "id";
    private static final String nickname = "nickname";
    private static final String profileImage = "profileImage";
    private static final String accessToken = "123";

    private KakaoLoginService kakaoLoginService;

    private static class StubKakaoClient implements KakaoClient {

        @Override
        public KakaoUserResponse getUserInfo(String accessToken) {
            return new KakaoUserResponse(id, new KakaoUserResponse.Properties(nickname, profileImage));
        }
    }

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
//        KakaoClient kakaoClient  = Mockito.mock(KakaoClient.class);
//        kakaoLoginService = new KakaoLoginService(kakaoClient, userRepository);

        //mock을 사용하고 싶은데 mock을 사용하면 null에러가 뜹니다...
        //혹시 저렇게 상속받는 방법 밖에 없을까요..
        //아  mock을 쓰려면 저 request 요청에 담긴 즉 kakaoclient에 있는걸 그냥 쓰면 되니까
        // 저렇게 값을 다 넣어줄꺼면 mock말고 저렇게 상속받는게 더 좋은 방법인건가욤...??

        kakaoLoginService = new KakaoLoginService(new StubKakaoClient(), userRepository);
    }

    @AfterEach
    public void clear() {
        userRepository.deleteAll();
    }

    @Test
    public void handleAuthentication_UserIsNotMemberFromDB_saveTheMember() {

        //given

        //when
        kakaoLoginService.handleAuthentication(AuthRequest.testInstance(accessToken));

        //then
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(1);
        User testUser = users.get(0);
        User compareUser = User.newKaKaoInstance(id, nickname, profileImage);
        assertThat(testUser.getSocialId()).isEqualTo(compareUser.getSocialId());

        //assertThat(testUser).isSameAs(compareUser);

        //이렇게 동일성말고 동등성으로 체크하고 싶은데 user를 새로 생성하면 새로운 객체가 돼서 한번에 검증이 안되네요...
        //하나씩 .getSocialId() 이런식으로 검증해야할까요...리팩토링 할 수 있을 거같은데...
    }

    @Test
    public void signUpUser_UserWantToSignUp_SaveDB() {
        //given
        User newUser = User.newKaKaoInstance(id, nickname, profileImage);

        //when
        userRepository.save(newUser);

        //then
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(1);
        User testUser = users.get(0);
        assertThat(testUser.getSocialId()).isEqualTo(newUser.getSocialId());
        assertThat(testUser.getProfileUrl()).isEqualTo(newUser.getProfileUrl());

//        assertThat(testUser).isSameAs(newUser);

        //질문
        //로그인을 이렇게 구현해도 되는건지, request 요청 자체를 mock으로 받아와서
        //token에 "토큰받은 암호같이 긴 것" 을 넣고 진짜 돌려 받는지 확인해야하는건지.

    }

}
