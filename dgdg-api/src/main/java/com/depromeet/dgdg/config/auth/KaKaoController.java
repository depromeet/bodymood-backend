package com.depromeet.dgdg.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class KaKaoController {

    @Autowired
    private KakaoService kakaoService;

    @RequestMapping("/login")
    public String home(@RequestParam(value = "code", required = false) String code) throws Exception {
        System.out.println("#########" + code);
        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        System.out.println("###access_Token### : " + access_Token);
        System.out.println("###userInfo### : " + userInfo.get("email"));
        System.out.println("###nickname### : " + userInfo.get("nickname"));
        System.out.println("###profile_image### : " + userInfo.get("profile_image"));
        return "testPage";
    }
}
