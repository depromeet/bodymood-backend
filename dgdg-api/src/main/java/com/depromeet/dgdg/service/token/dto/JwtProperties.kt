package com.depromeet.dgdg.service.token.dto

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("jwt")
data class JwtProperties(
    val issuer: String,
    val secret: String,
    val expiresTime: Long
)
