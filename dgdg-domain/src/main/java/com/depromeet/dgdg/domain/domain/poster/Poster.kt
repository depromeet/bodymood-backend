package com.depromeet.dgdg.domain.domain.poster

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import com.depromeet.dgdg.domain.domain.user.User
import javax.persistence.*

@Entity
class Poster(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Column(nullable = false)
    var imageUrl: String,

    @Column(nullable = false)
    val originImageUrl: String,

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    val emotion: Emotion,

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    var posterStatus: PosterStatus = PosterStatus.ACTIVE
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    companion object {
        fun of(user: User, imageUrl: String, originImageUrl: String, emotion: String): Poster {
            return Poster(
                user,
                imageUrl,
                originImageUrl,
                Emotion.fromCode(emotion)
            )
        }
    }

    fun updatePoster(newImageUrl: String) {
        imageUrl = newImageUrl
    }

    fun delete() {
        this.posterStatus = PosterStatus.DELETED
    }

}
