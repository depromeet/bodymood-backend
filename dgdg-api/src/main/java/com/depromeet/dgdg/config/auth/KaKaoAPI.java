package com.depromeet.dgdg.config.auth;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class KaKaoAPI {

//    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private final String clientId;

//    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private final String redirectUri;

//    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private final String grantType;

//    @Value("${spring.security.oauth2.client.provider.kakao.token_uri}")
    private final String tokenUri;

//    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private final String userInfoUri;


    public String getUserInfo(String accessToken) throws Exception {

        String socialId = "";

        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create(userInfoUri);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + accessToken);
        headers.set("KakaoAk", clientId);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        params.add("properties_key", "[\"id\"]");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
        ResponseEntity<JSONObject> apiResponse = restTemplate.postForEntity(uri, request, JSONObject.class);

        JSONObject responseBody = apiResponse.getBody();

        socialId = Integer.toString((Integer) responseBody.get("id"));

        return socialId;

    }

}
