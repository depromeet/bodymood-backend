package com.depromeet.dgdg.config.kakaoLogin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String accessToken;

}
