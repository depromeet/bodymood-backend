package com.depromeet.dgdg.domain.domain.tag

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import com.depromeet.dgdg.domain.domain.memo.Memo
import javax.persistence.*

@Entity
class MemoTag(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_id", nullable = false)
    var memo: Memo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    var tag: Tag
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

}
