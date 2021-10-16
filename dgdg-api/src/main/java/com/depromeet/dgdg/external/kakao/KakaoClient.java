package com.depromeet.dgdg.external.kakao;

import com.depromeet.dgdg.external.kakao.dto.response.KakaoUserResponse;

public interface KakaoClient {

    KakaoUserResponse getUserInfo(String accessToken);

}
