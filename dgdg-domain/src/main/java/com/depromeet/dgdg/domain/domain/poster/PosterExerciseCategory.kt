package com.depromeet.dgdg.domain.domain.poster

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategory
import javax.persistence.*

@Entity
class PosterExerciseCategory(
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "poster_id")
    val poster: Poster,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "exercise_category_id")
    val exerciseCategory: ExerciseCategory

) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    companion object{
        @JvmStatic
        fun of(poster:Poster, exerciseCategory: ExerciseCategory): PosterExerciseCategory{
            return PosterExerciseCategory(poster, exerciseCategory)
        }
    }
}
