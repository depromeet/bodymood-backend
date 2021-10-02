package com.depromeet.dgdg.domain.domain.tag

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class Tag(
    @Enumerated(EnumType.STRING)
    var type: TagType,

    var name: String
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

}
