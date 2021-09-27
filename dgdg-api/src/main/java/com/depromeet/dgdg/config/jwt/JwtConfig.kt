package com.depromeet.dgdg.config.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("jwt")
data class JwtConfig(
    val issuer: String,
    val secret: String,
)
