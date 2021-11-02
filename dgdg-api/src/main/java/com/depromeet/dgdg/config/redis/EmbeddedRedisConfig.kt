package com.depromeet.dgdg.config.redis

import com.depromeet.dgdg.common.utils.ProcessUtils.findAvailableRandomPort
import com.depromeet.dgdg.common.utils.logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Profile("local", "local-pg")
@Configuration
class EmbeddedRedisConfig(
    private val property: RedisProperty,
) {

    private lateinit var redisServer: RedisServer
    private var port: Int = 0

    @PostConstruct
    fun redisServer() {
        port = findAvailableRandomPort()
        redisServer = RedisServer(port)
        redisServer.run {
            this.start()
            log.info("임베디드 레디스 서버가 기동되었습니다. port: $port")
        }
    }

    @PreDestroy
    fun stopRedis() {
        redisServer.run {
            this.stop()
            log.info("임베디드 레디스 서버가 종료됩니다. port: $port")
        }
    }

    @Bean
    fun embeddedRedisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(property.host, port)
    }

    companion object {
        private val log = logger()
    }

}
