package com.depromeet.dgdg.controller;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T> {

    public static final BaseResponse<String> OK = success("OK");
    public static final BaseResponse<String> HEALTH_CHECK = success("득근득근 API 서버");

    private String code;

    private String message;

    private T data;

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>("0000", "성공", data);
    }

}
