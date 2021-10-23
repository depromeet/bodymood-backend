package com.depromeet.dgdg.controller.exercise

import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategory
import com.depromeet.dgdg.controller.exercise.dto.response.ExerciseCategoryResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExerciseCategoryController {

    @GetMapping("/api/v1/exercise/categories")
    fun getExerciseCategories(): List<ExerciseCategoryResponse> {
        return ExerciseCategory.values().asSequence()
            .filter { it.isActivated }
            .sortedBy { it.displayOrder }
            .map { ExerciseCategoryResponse(it.title, it.description) }
            .toList()
    }

}
