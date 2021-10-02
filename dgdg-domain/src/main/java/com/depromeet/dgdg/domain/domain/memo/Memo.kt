package com.depromeet.dgdg.domain.domain.memo

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class Memo(
    // TODO: 도메인 정해진 후에 개발
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

}
