package com.depromeet.dgdg.service.exercise

import com.depromeet.dgdg.domain.domain.exercise.ExerciseRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExerciseService(
    private val exerciseRepository: ExerciseRepository
) {

    @Transactional(readOnly = true)
    fun getExercises() {
    }

}
