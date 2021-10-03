package com.depromeet.dgdg.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final OAuth2Kakao oAuth2Kakao;


    @GetMapping("/auth/authorization/kakao")
    public String oAuth2AuthorizationKakao(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String code = request.getParameter("code");
        String error = request.getParameter("error");
        if (error != null) {
            if (error.equals("access_denied")) {
                return "취소";
            }
        }

        String accessToken = oAuth2Kakao.getAccessToken(code);
        String userInfo = oAuth2Kakao.getUserInfo(accessToken);

        if (userInfo != null && !userInfo.equals("")) {
            return "redirect:/";
        } else {
            throw new UnsupportedOperationException("카카오톡 정보조회에 실패했습니다.");
        }
    }
}
