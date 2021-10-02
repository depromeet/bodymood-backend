package com.depromeet.dgdg.domain.domain.memo

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import com.depromeet.dgdg.domain.domain.tag.MemoTag
import com.depromeet.dgdg.domain.domain.tag.Tag
import javax.persistence.*

@Entity
class Memo(
    // TODO: 도메인 정해진 후에 개발
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @OneToMany(mappedBy = "memo", cascade = [CascadeType.ALL], orphanRemoval = true)
    val memoTags: MutableList<MemoTag> = mutableListOf()

    fun addTags(tags: List<Tag>) {
        for (tag in tags) {
            addTag(tag)
        }
    }

    private fun addTag(tag: Tag) {
        this.memoTags.add(MemoTag(this, tag))
    }

}
