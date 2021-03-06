package com.depromeet.dgdg.service.exercise

import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategoryRepository
import com.depromeet.dgdg.service.exercise.dto.response.ExerciseCategoryResponse
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExerciseCategoryService(
    private val exerciseCategoryRepository: ExerciseCategoryRepository
) {

    @Cacheable(value = ["exercise-category"], key = "'ALL'")
    @Transactional(readOnly = true)
    fun getExerciseCategories(): List<ExerciseCategoryResponse> {
        return exerciseCategoryRepository.findRootCategories()
            .map { ExerciseCategoryResponse.of(it) }
    }

}
