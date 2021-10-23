package com.depromeet.dgdg.domain.domain.poster

import com.depromeet.dgdg.domain.domain.common.Uuid
import javax.persistence.*

@Entity
class Poster(
    @Column(nullable = false)
    val userId: Long
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    @Embedded
    val uuid: Uuid = Uuid.newInstance()

}
