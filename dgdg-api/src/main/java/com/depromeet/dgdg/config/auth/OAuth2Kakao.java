package com.depromeet.dgdg.config.auth;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;


@Service
@RequiredArgsConstructor
public class OAuth2Kakao {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private final String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private final String redirectUri;

    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private final String grantType;

    @Value("${spring.security.oauth2.client.provider.kakao.token_uri}")
    private final String tokenUri;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private final String userInfoUri;

    public String getAccessToken(String code) throws Exception {

        String accessToken = "";

        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create(tokenUri);

        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        params.set("grant_type", grantType);
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri + "/login/oauth_kakao");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<>(params, headers);

        ResponseEntity<JSONObject> apiResponse = restTemplate.postForEntity(uri, restRequest, JSONObject.class);
        JSONObject responseBody = apiResponse.getBody();

        accessToken = (String) responseBody.get("access_token");

        return accessToken;
    }


    public String getUserInfo(String accessToken) {

        String userInfo = "";

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

        userInfo = Integer.toString(responseBody.getInt("id"));

        return userInfo;

    }
}
