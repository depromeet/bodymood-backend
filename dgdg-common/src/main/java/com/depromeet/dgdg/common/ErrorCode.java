package com.depromeet.dgdg.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    UNAUTHORIZED_EXCEPTION(401, "UNAUTHORIZED_EXCEPTION", "잘못된 토큰입니다. 다시 로그인해주세요"),
    TOKEN_EXPIRED_EXCEPTION(401, "TOKEN_EXPIRED_EXCEPTION", "토큰이 만료되었습니다."),
    INTERNAL_SERVER_EXCEPTION(500, "INTERNAL_SERVER_EXCEPTION", "서버 내부에서 에러가 발생하였습니다")
    ;

    private final int status;
    private final String code;
    private final String message;

}
