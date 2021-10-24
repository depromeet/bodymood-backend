package com.depromeet.dgdg.domain.domain.exercise

import com.depromeet.dgdg.common.exception.ForbiddenException
import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class ExerciseCategory(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_category_id")
    val parentCategory: ExerciseCategory?,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val depth: Int
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @OneToMany(mappedBy = "parentCategory", cascade = [CascadeType.ALL], orphanRemoval = true)
    val childrenCategories: MutableList<ExerciseCategory> = mutableListOf()

    fun addChildCategory(name: String, description: String) {
        if (this.depth >= MAX_DEPTH) {
            throw ForbiddenException("현재 카테고리의 depth는 (${this.depth + 1})로, 현재 버전에서 (${MAX_DEPTH})이상 넘을 수 없습니다.")
        }
        this.childrenCategories.add(ExerciseCategory(this, name, description, this.depth + 1))
    }

    companion object {
        private const val MAX_DEPTH = 2

        fun newRootCategory(name: String, description: String): ExerciseCategory {
            return ExerciseCategory(null, name, description, 1)
        }
    }

}
