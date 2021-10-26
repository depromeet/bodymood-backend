package com.depromeet.dgdg.domain.exercise

import com.depromeet.dgdg.common.exception.ForbiddenException
import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategory
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThatThrownBy

internal class ExerciseCategoryTest : FunSpec({

    context("카테고리를 생성한다") {
        test("depth가 0이하인 경우 생성할 수 없다 throws ForbiddenException") {
            // when & then
            assertThatThrownBy {
                ExerciseCategory(
                    null,
                    "name",
                    "description",
                    0
                )
            }.isInstanceOf(ForbiddenException::class.java)
        }

        test("최상위 카테고리를 생성하면 depth가 1이고 parentCategory가 null인 카테고리가 생성된다") {
            // given
            val englishName = "Shoulder"
            val koreanName = "어깨 운동"

            // when
            val rootCategory = ExerciseCategory.newRootCategory(englishName, koreanName)

            // then
            rootCategory.parentCategory shouldBe null
            rootCategory.englishName shouldBe englishName
            rootCategory.koreanName shouldBe koreanName
            rootCategory.depth shouldBe 1
        }
    }

    context("하위 카테고리를 추가한다") {
        test("하위 카테고리를 추가하면 depth가 1더 큰 카테고리가 생성된다") {
            // given
            val rootCategory = ExerciseCategory.newRootCategory("1depth", "1depth")

            // when
            rootCategory.addChildCategory("2뎁스", "2depth")

            // then
            rootCategory.childrenCategories shouldHaveSize 1
            rootCategory.childrenCategories[0].englishName shouldBe "2뎁스"
            rootCategory.childrenCategories[0].depth shouldBe 2
        }

        test("최대 뎁스인 2를 넘어서 카테고리를 추가 할 수 없다 throws ForbiddenException") {
            // given
            val rootCategory = ExerciseCategory.newRootCategory("1depth", "1depth")
            rootCategory.addChildCategory("2뎁스", "2depth")

            // when & then
            assertThatThrownBy { rootCategory.childrenCategories[0].addChildCategory("3뎁스", "3depth") }
                .isInstanceOf(ForbiddenException::class.java)
        }
    }

})
