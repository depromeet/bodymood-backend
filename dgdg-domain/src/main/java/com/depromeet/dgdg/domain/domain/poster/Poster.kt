package com.depromeet.dgdg.domain.domain.poster

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class Poster(
    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val imageUrl: String,

    @Column(nullable = false)
    val originImageUrl: String,

    @Enumerated(EnumType.STRING)
    val emotion: Emotion
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}
