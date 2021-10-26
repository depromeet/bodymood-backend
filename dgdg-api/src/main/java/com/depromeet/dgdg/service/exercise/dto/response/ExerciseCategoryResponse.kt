package com.depromeet.dgdg.service.exercise.dto.response

import com.depromeet.dgdg.controller.dto.response.BaseTimeResponse
import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategory

data class ExerciseCategoryResponse(
    val categoryId: Long,
    val name: String,
    val description: String,
    val depth: Int,
    val children: List<ExerciseCategoryResponse>
) : BaseTimeResponse() {

    companion object {
        fun of(category: ExerciseCategory): ExerciseCategoryResponse {
            val response = ExerciseCategoryResponse(
                category.id,
                category.name,
                category.description,
                category.depth,
                category.childrenCategories
                    .map { of(it) }
            )
            response.setBaseTime(category)
            return response
        }
    }

}
