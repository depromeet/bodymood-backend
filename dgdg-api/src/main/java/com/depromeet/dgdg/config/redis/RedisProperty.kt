package com.depromeet.dgdg.config.redis

import org.springframework.boot.context.properties.ConfigurationProperties

import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties("spring.redis")
data class RedisProperty(
    val host: String,
    val port: Int
)
