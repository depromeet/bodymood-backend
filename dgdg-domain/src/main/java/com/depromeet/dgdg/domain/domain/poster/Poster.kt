package com.depromeet.dgdg.domain.domain.poster

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import com.depromeet.dgdg.domain.domain.user.User
import javax.persistence.*

@Entity
class Poster(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

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

    companion object{
        fun of(user: User, imageUrl: String, originImageUrl: String, emotion: String) : Poster {
            return Poster(
                user,
                imageUrl,
                originImageUrl,
                Emotion.valueOf(emotion)
            )
        }
    }
}
