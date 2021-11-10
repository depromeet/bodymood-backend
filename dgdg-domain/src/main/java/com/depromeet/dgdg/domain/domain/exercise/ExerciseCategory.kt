package com.depromeet.dgdg.domain.domain.exercise

import com.depromeet.dgdg.common.ErrorCode.FORBIDDEN_EXERCISE_CATEGORY_DEPTH_EXCEPTION
import com.depromeet.dgdg.common.exception.ForbiddenException
import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class ExerciseCategory(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parentCategory: ExerciseCategory?,

    @Column(nullable = false, length = 50)
    var englishName: String,

    @Column(nullable = false, length = 50)
    var koreanName: String,

    val depth: Int = 1
) : BaseTimeEntity() {

    init {
        if (depth !in MIN_DEPTH..MAX_DEPTH) {
            throw ForbiddenException(
                "depth (${depth})는 (${MIN_DEPTH} ~ ${MAX_DEPTH}) 사이에서만 허용됩니다",
                FORBIDDEN_EXERCISE_CATEGORY_DEPTH_EXCEPTION
            )
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @OneToMany(mappedBy = "parentCategory", cascade = [CascadeType.ALL], orphanRemoval = true)
    val childrenCategories: MutableList<ExerciseCategory> = mutableListOf()

    fun addChildCategory(englishName: String, koreanName: String) {
        this.childrenCategories.add(ExerciseCategory(this, englishName, koreanName, this.depth + 1))
    }

    companion object {
        private const val MIN_DEPTH = 1
        private const val MAX_DEPTH = 2

        fun newRootCategory(englishName: String, koreanName: String): ExerciseCategory {
            return ExerciseCategory(null, englishName, koreanName, 1)
        }
    }

}
