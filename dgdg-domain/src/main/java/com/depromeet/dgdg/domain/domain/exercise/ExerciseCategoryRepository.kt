package com.depromeet.dgdg.domain.domain.exercise

import com.depromeet.dgdg.domain.domain.exercise.repository.ExerciseRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseCategoryRepository : JpaRepository<ExerciseCategory, Long>, ExerciseRepositoryCustom
