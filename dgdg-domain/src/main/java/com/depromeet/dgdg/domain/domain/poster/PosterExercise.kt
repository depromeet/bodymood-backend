package com.depromeet.dgdg.domain.domain.poster

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class PosterExercise(
    @Column(nullable = false)
    val posterId: Long,

    @Column(nullable = false)
    val exerciseId: Long
): BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}
