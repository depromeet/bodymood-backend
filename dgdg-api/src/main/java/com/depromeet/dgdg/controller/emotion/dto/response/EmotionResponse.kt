package com.depromeet.dgdg.controller.emotion.dto.response

import com.depromeet.dgdg.domain.domain.poster.Emotion

data class EmotionResponse(
    val type: Emotion,
    val englishTitle: String,
    val koreanTitle: String,
    val startColor: String,
    val endColor: String,
    val fontColor: String
) {

    companion object {
        fun of(emotion: Emotion): EmotionResponse {
            return EmotionResponse(
                emotion,
                emotion.englishTitle,
                emotion.koreanTitle,
                emotion.startColor,
                emotion.endColor,
                emotion.fontColor.fontColor
            )
        }
    }

}
