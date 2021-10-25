package com.depromeet.dgdg.external.apple.dto.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("apple")
data class AppleAuthProperties(
    val issuer: String,
    val clientId: String
)
