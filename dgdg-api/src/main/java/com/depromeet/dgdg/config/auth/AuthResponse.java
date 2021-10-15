package com.depromeet.dgdg.config.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class AuthResponse {

    private final String accessToken;
    private final String refreshToken;

    public static AuthResponse of(String accessToken, String refreshToken) {
        return new AuthResponse(accessToken, refreshToken);
    }

}
