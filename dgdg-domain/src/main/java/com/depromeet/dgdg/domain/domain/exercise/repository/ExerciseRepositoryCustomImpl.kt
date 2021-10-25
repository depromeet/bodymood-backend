package com.depromeet.dgdg.domain.domain.exercise.repository

import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategory
import com.depromeet.dgdg.domain.domain.exercise.QExerciseCategory
import com.depromeet.dgdg.domain.domain.exercise.QExerciseCategory.exerciseCategory
import com.querydsl.jpa.impl.JPAQueryFactory

class ExerciseRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : ExerciseRepositoryCustom {

    override fun findRootCategories(): List<ExerciseCategory> {
        return queryFactory.selectFrom(exerciseCategory).distinct()
            .innerJoin(exerciseCategory.childrenCategories, QExerciseCategory("parentCategory")).fetchJoin()
            .where(
                exerciseCategory.parentCategory.isNull
            )
            .fetch()
    }

}
