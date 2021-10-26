package com.depromeet.dgdg.controller.emotion

import com.depromeet.dgdg.controller.emotion.dto.response.EmotionResponse
import com.depromeet.dgdg.domain.domain.poster.Emotion
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.sequences.containExactly
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@AutoConfigureMockMvc
@SpringBootTest
internal class EmotionControllerTest(
    private val mockMvc: MockMvc
) : FunSpec({

    context("GET /api/v1/emotions/categories") {
        test("감정 카테고리를 조회한다") {
            mockMvc.get("/api/v1/emotions/categories")
                .andDo { print() }
                .andExpect { status { isOk() } }
                .andExpect { jsonPath("$.data") { hasSize<EmotionResponse>(Emotion.values().size) } }
                .andExpect { jsonPath("$.data") { containExactly(Emotion.values().map { EmotionResponse.of(it) }) } }
        }
    }

})
