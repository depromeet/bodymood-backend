package com.depromeet.dgdg.service.token.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("jwt")
data class JwtProperty(
    val issuer: String,
    val secret: String,
)
