package com.depromeet.dgdg.controller.emotion.dto.response

import com.depromeet.dgdg.domain.domain.poster.Emotion

data class EmotionResponse(
    val type: Emotion,
    val title: String,
    val description: String,
    val startColor: String,
    val endColor: String,
    val fontColor: String
) {

    companion object {
        fun of(emotion: Emotion): EmotionResponse {
            return EmotionResponse(
                emotion,
                emotion.title,
                emotion.description,
                emotion.startColor,
                emotion.endColor,
                emotion.fontColor.fontColor
            )
        }
    }

}
