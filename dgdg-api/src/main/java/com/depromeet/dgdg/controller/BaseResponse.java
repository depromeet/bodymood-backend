package com.depromeet.dgdg.controller;

public class BaseResponse<T> {

    public static final BaseResponse<String> OK = success("OK");

    private String code;

    private String message;

    private T data;

    private BaseResponse() { /* no-ops */}

    private BaseResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>("", "", data);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
