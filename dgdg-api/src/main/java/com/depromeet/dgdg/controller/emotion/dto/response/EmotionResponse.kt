package com.depromeet.dgdg.controller.emotion.dto.response

import com.depromeet.dgdg.domain.domain.poster.Emotion

data class EmotionResponse(
    val title: String,
    val description: String,
    val startColor: String,
    val endColor: String
) {

    companion object {
        fun of(emotion: Emotion): EmotionResponse {
            return EmotionResponse(emotion.title, emotion.description, emotion.startColor, emotion.endColor)
        }
    }

}
