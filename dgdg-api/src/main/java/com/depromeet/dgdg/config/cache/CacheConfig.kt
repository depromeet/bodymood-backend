package com.depromeet.dgdg.config.cache

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Profile("local-pg", "dev", "prod")
@EnableCaching
@Configuration
class CacheConfig(
    private val redisConnectionFactory: RedisConnectionFactory,
    private val objectMapper: ObjectMapper
) : CachingConfigurerSupport() {

    override fun cacheManager(): CacheManager {
        val builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
        return builder.cacheDefaults(defaultCacheConfiguration())
            .withInitialCacheConfigurations(customCacheConfiguration())
            .build()
    }

    private fun defaultCacheConfiguration(): RedisCacheConfiguration {
        return RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    GenericJackson2JsonRedisSerializer(objectMapper)
                )
            )
            .entryTtl(Duration.ofHours(1))
    }

    private fun customCacheConfiguration(): Map<String, RedisCacheConfiguration> {
        val policies = hashMapOf<String, RedisCacheConfiguration>()
        for (cache in CacheType.values()) {
            policies[cache.key] = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
                .serializeValuesWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(
                        GenericJackson2JsonRedisSerializer(objectMapper)
                    )
                )
                .entryTtl(cache.duration)
        }
        return policies
    }

}
