package com.depromeet.dgdg.service.exercise.dto.response

import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategory

data class ExerciseCategoryResponse(
    val categoryId: Long,
    val name: String,
    val description: String,
    val depth: Int,
    val children: List<ExerciseCategoryResponse>
) {

    companion object {
        fun of(category: ExerciseCategory): ExerciseCategoryResponse {
            return ExerciseCategoryResponse(
                category.id,
                category.name,
                category.description,
                category.depth,
                category.childrenCategories
                    .map { of(it) }
            )
        }
    }

}
