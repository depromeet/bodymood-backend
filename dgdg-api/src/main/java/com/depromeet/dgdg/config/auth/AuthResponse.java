package com.depromeet.dgdg.config.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class AuthResponse {
    private final String socialId;

    public static AuthResponse of(String socialId) {
        return new AuthResponse(socialId);
    }
}
