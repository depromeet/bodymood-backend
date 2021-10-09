package com.depromeet.dgdg.domain.domain.tag

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class Tag(
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: TagType,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: TagStatus = TagStatus.ACTIVE
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

}
