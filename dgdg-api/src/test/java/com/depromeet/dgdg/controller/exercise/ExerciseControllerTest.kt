package com.depromeet.dgdg.controller.exercise

import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategory
import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategoryRepository
import com.depromeet.dgdg.service.exercise.dto.response.ExerciseCategoryResponse
import io.kotest.core.spec.style.FunSpec
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@AutoConfigureMockMvc
@SpringBootTest
class ExerciseControllerTest(
    private val mockMvc: MockMvc,
    private val exerciseCategoryRepository: ExerciseCategoryRepository
) : FunSpec({

    afterEach {
        exerciseCategoryRepository.deleteAll()
    }

    context("GET /api/v1/exercises") {
        test("루트 카테고리부터 계층적으로 조회한다") {
            // given
            val exerciseCategory1 = ExerciseCategory.newRootCategory("1단계-1", "어깨")
            val exerciseCategory2 = ExerciseCategory.newRootCategory("1단계-2", "어깨")

            exerciseCategory1.addChildCategory("2단계-1", "백 플라이")
            exerciseCategory1.addChildCategory("2단계-2", "백 플라이")

            exerciseCategory2.addChildCategory("2단계-3", "백 플라이")
            exerciseCategoryRepository.saveAll(listOf(exerciseCategory1, exerciseCategory2))

            // when & then
            mockMvc.get("/api/v1/exercises")
                .andDo { print() }
                .andExpect { status { isOk() } }
                .andExpect {
                    jsonPath("$.data") { hasSize<ExerciseCategoryResponse>(2) }
                    jsonPath("$.data[0].name") { value("1단계-1") }
                    jsonPath("$.data[0].depth") { value(1) }

                    jsonPath("$.data[0].children") { hasSize<ExerciseCategoryResponse>(2) }
                    jsonPath("$.data[0].children[0].name") { value("2단계-1") }
                    jsonPath("$.data[0].children[0].depth") { value(2) }
                    jsonPath("$.data[0].children[1].name") { value("2단계-2") }
                    jsonPath("$.data[0].children[1].depth") { value(2) }

                    jsonPath("$.data[1].children") { hasSize<ExerciseCategoryResponse>(1) }
                    jsonPath("$.data[1].children[0].name") { value("2단계-3") }
                    jsonPath("$.data[1].children[0].depth") { value(2) }
                }
        }
    }

})
