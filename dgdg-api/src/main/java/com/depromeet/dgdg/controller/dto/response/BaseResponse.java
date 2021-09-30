package com.depromeet.dgdg.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T> {

    public static final BaseResponse<String> OK = success("OK");
    public static final BaseResponse<String> HEALTH_CHECK = success("득근득근 API 서버");

    private final String code;

    private final String message;

    private final T data;

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>("0000", "성공", data);
    }

    public static <T> BaseResponse<T> error(String code, String message) {
        return new BaseResponse<>(code, message, null);
    }

}
