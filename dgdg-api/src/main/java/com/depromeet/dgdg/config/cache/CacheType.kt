package com.depromeet.dgdg.config.cache

import java.time.Duration

enum class CacheType(
    val key: String,
    val duration: Duration
) {

    EXERCISE_CATEGORY(CacheKey.EXERCISE_CATEGORY, Duration.ofHours(1)),
    ;

    object CacheKey {
        const val EXERCISE_CATEGORY = "EXERCISE_CATEGORY"
    }

}
