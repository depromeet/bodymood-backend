package com.depromeet.dgdg.service.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank
    private String accessToken;

    public static AuthRequest testInstance(String accessToken) {
        return new AuthRequest(accessToken);
    }

}
