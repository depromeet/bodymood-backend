package com.depromeet.dgdg.controller.exercise

import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.service.exercise.ExerciseCategoryService
import com.depromeet.dgdg.service.exercise.dto.response.ExerciseCategoryResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExerciseCategoryController(
    private val exerciseCategoryService: ExerciseCategoryService
) {

    @GetMapping("/api/v1/exercises/categories")
    fun getExerciseCategories(): BaseResponse<List<ExerciseCategoryResponse>> {
        return BaseResponse.success(exerciseCategoryService.getExerciseCategories())
    }

}
