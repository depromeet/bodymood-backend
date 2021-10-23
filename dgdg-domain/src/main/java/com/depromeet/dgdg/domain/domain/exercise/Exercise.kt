package com.depromeet.dgdg.domain.domain.exercise

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class Exercise(
    @Enumerated(EnumType.STRING)
    val category: ExerciseCategory,

    @Column(nullable = false)
    val type: String
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}
