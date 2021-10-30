package com.depromeet.dgdg.domain.domain.poster

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import com.depromeet.dgdg.domain.domain.user.User
import com.sun.istack.NotNull
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
class Poster(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    @Column(nullable = false)
    var imageUrl: String,
    //이 부분 val에서 var로 바꿨습니다. 나중에 혹시 문제될까봐 바뀐부분 아래 주석 달겠습니다
    //아래 updatePoster 함수 만들기위해 만든 것입니다.

    @Column(nullable = false)
    val originImageUrl: String,

    @Enumerated(EnumType.STRING)
    val emotion: Emotion
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

    fun updatePoster(@NotNull newImageUrl: String) {
        imageUrl = newImageUrl
    }
}
