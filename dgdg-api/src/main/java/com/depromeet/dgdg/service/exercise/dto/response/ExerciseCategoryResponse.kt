package com.depromeet.dgdg.service.exercise.dto.response

import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategory

data class ExerciseCategoryResponse(
    val categoryId: Long,
    val englishName: String,
    val koreanName: String,
    val depth: Int,
    val children: List<ExerciseCategoryResponse>
) {

    companion object {
        fun of(category: ExerciseCategory): ExerciseCategoryResponse {
            return ExerciseCategoryResponse(
                category.id,
                category.englishName,
                category.koreanName,
                category.depth,
                category.childrenCategories
                    .map { of(it) }
            )
        }
    }

}
