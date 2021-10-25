package com.depromeet.dgdg.controller.emotion

import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.controller.emotion.dto.response.EmotionResponse
import com.depromeet.dgdg.domain.domain.poster.Emotion
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EmotionController {

    @GetMapping("/api/v1/emotions/categories")
    fun getEmotions(): BaseResponse<List<EmotionResponse>> {
        return BaseResponse.success(Emotion.values()
            .map { EmotionResponse.of(it) })
    }

}
