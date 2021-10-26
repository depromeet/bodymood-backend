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
    var name: String,

    @Column(nullable = false)
    var description: String,

    depth: Int = 1
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val depth: Int = depth

    @OneToMany(mappedBy = "parentCategory", cascade = [CascadeType.ALL], orphanRemoval = true)
    val childrenCategories: MutableList<ExerciseCategory> = mutableListOf()

    init {
        if (MIN_DEPTH > depth || depth > MAX_DEPTH) {
            throw ForbiddenException("depth (${depth})는 (${MIN_DEPTH} ~ ${MAX_DEPTH}) 사이에서만 허용됩니다")
        }
    }

    fun addChildCategory(name: String, description: String) {
        this.childrenCategories.add(ExerciseCategory(this, name, description, this.depth + 1))
    }

    companion object {
        private const val MIN_DEPTH = 1
        private const val MAX_DEPTH = 2

        fun newRootCategory(name: String, description: String): ExerciseCategory {
            return ExerciseCategory(null, name, description, 1)
        }
    }

}
