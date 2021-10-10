package com.depromeet.dgdg.external.kakao.dto.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("spring.security.oauth2.client.provider.kakao")
public class KakaoProperties {

    private String userInfoUri;

}
