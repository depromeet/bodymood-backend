package com.depromeet.dgdg.controller;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T> {

    public static final BaseResponse<String> OK = success("OK");

    private String code;

    private String message;

    private T data;

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>("", "", data);
    }

}
