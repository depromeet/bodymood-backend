package com.depromeet.dgdg.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    // Bad Request
    BAD_REQUEST_EXCEPTION(400, "B001", "잘못된 요청입니다"),
    BAD_REQUEST_MISSING_REQUIRED_VALUE_EXCEPTION(400, "B002", "필수 값을 입력해주세요"),

    // UnAuthorized
    UNAUTHORIZED_EXCEPTION(401, "U001", "잘못된 토큰입니다. 다시 로그인해주세요"),
    TOKEN_EXPIRED_EXCEPTION(401, "U002", "토큰이 만료되었습니다"),

    // Forbidden
    FORBIDDEN_EXCEPTION(403, "F001", "허용되지 않은 접근입니다"),

    // NotFound
    NOT_FOUND_EXCEPTION(404, "N001", "존재하지 않습니다"),
    NOT_FOUND_USER_EXCEPTION(404, "N002", "존재하지 않는 유저입니다"),

    // Method Not Allowed
    METHOD_NOT_ALLOWED_EXCEPTION(405, "M001", "Method Not allowed"),

    // Conflict
    CONFLICT_EXCEPTION(409, "C001", "이미 존재합니다"),

    // Unsupported Media Type
    UNSUPPORTED_MEDIA_TYPE(415, "T001", "Unsupported Media Type"),

    // Internal Server
    INTERNAL_SERVER_EXCEPTION(500, "I001", "서버 내부에서 에러가 발생하였습니다"),

    // Service Unavailable
    SERVICE_UNAVAILABLE_EXCEPTION(503, "S001", "점검중입니다"),
    ;

    private final int status;
    private final String code;
    private final String message;

}
