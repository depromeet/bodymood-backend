package com.depromeet.dgdg.domain.domain.exercise.repository

import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategory

interface ExerciseRepositoryCustom {

    fun findRootCategories(): List<ExerciseCategory>

}
