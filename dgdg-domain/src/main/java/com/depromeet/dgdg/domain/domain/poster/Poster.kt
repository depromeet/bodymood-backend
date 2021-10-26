package com.depromeet.dgdg.domain.domain.poster

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import com.depromeet.dgdg.domain.domain.user.User
import javax.persistence.*

// TODO : 감정 및 운동 카테고리 추가
@Entity
class Poster(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User?,

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
