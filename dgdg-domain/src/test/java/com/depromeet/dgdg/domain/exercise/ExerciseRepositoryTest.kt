package com.depromeet.dgdg.domain.exercise

import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategory
import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategoryRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ExerciseRepositoryTest(
    private val exerciseCategoryRepository: ExerciseCategoryRepository
) : FunSpec({

    afterEach {
        exerciseCategoryRepository.deleteAll()
    }

    context("findRootCategories") {
        test("저장된 루트 카테고리를 모두 조회한다") {
            // given
            val exerciseCategory = ExerciseCategory.newRootCategory("Shoulder", "어깨")

            exerciseCategory.addChildCategory("Back fly", "백 플라이")
            exerciseCategoryRepository.save(exerciseCategory)

            // when
            val categories = exerciseCategoryRepository.findRootCategories()

            // then
            categories shouldHaveSize 1
            categories[0].also {
                it.englishName shouldBe "Shoulder"
                it.koreanName shouldBe "어깨"
                it.depth shouldBe 1

                it.childrenCategories shouldHaveSize 1
                it.childrenCategories[0].englishName shouldBe "Back fly"
                it.childrenCategories[0].depth shouldBe 2
            }
        }
    }

})
