package com.depromeet.dgdg.controller.emotion

import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.controller.emotion.dto.response.EmotionResponse
import com.depromeet.dgdg.domain.domain.poster.Emotion
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EmotionController {

    @Operation(summary = "감정 카테고리 종류를 조회하는 API")
    @GetMapping("/api/v1/emotions/categories")
    fun getEmotions(): BaseResponse<List<EmotionResponse>> {
        return BaseResponse.success(Emotion.values()
            .map { EmotionResponse.of(it) })
    }

}
