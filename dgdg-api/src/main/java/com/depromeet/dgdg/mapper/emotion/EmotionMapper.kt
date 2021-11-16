package com.depromeet.dgdg.mapper.emotion

import com.depromeet.dgdg.controller.emotion.dto.response.EmotionResponse
import com.depromeet.dgdg.domain.domain.poster.Emotion

object EmotionMapper {

    fun getEmotionCategories(): List<EmotionResponse> {
        return Emotion.values()
            .map { EmotionResponse.of(it) }
    }

}
