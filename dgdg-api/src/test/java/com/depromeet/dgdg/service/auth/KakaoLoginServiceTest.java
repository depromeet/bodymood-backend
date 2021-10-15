package com.depromeet.dgdg.service.auth;

import com.depromeet.dgdg.common.ErrorCode;
import com.depromeet.dgdg.common.exception.BadRequestException;
import com.depromeet.dgdg.domain.domain.user.User;
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository;
import com.depromeet.dgdg.external.kakao.KakaoClient;
import com.depromeet.dgdg.external.kakao.dto.response.KakaoUserResponse;
import com.depromeet.dgdg.service.auth.dto.request.AuthRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KakaoLoginServiceTest {

    @InjectMocks
    private KakaoLoginService kakaoLoginService;

    @Mock
    private KakaoClient kakaoClient;

    @Mock
    private UserRepository userRepository;

    private KakaoUserResponse kakaoUserResponse;
    private User user;

    @BeforeEach
    public void setUp() {
        kakaoUserResponse = new KakaoUserResponse("socialId", new KakaoUserResponse.Properties("nickname", "profileImage"));
        user = user.newKaKaoInstance("socialId", "name", "profileUrl");
    }


    @Test
    public void handleAuthentication_UserIsAlreadyExist_DontCallSaveMethodAndReturnId() throws NoSuchFieldException, IllegalAccessException {
        //when
        when(kakaoClient.getUserInfo(any())).thenReturn(kakaoUserResponse);
        when(userRepository.findBySocialIdAndSocialProvider(eq("socialId"), any())).thenReturn(Optional.of(user));


        Field field = User.class.getDeclaredField("id");
        field.setAccessible(true);
        field.set(user, 1L);

        //given
        Long comparedId = kakaoLoginService.handleAuthentication(new AuthRequest("token"));

        //then
        verify(userRepository, never()).save(any());
        Assertions.assertThat(user.getId()).isEqualTo(comparedId);
    }

    @Test
    public void signUpUser_UserIsNotOurMember_CallSaveMethod() {
        //when
        when(kakaoClient.getUserInfo(any())).thenReturn(kakaoUserResponse);
        when(userRepository.findBySocialIdAndSocialProvider(eq("socialId"), any())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(new User());

        //given
        kakaoLoginService.handleAuthentication(new AuthRequest("token"));

        verify(userRepository,times(1)).save(any());


    }

    @Test
    public void handleAuthentication_KakaoClientErr_DontCallSaveMethodAndFindBySocialIdAndSocialProvider() {
        when(kakaoClient.getUserInfo(any())).thenThrow(new BadRequestException("잘못된 요청입니다.", ErrorCode.BAD_REQUEST_EXCEPTION));

        try {
            kakaoLoginService.handleAuthentication(new AuthRequest("token"));
            fail();
        } catch (BadRequestException e) {

        }
        verify(userRepository, never()).save(any());
        verify(userRepository, never()).findBySocialIdAndSocialProvider(anyString(), any());

    }





}
