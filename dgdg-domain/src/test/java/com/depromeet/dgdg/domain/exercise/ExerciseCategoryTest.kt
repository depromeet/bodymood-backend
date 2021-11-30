package com.depromeet.dgdg.domain.exercise

import com.depromeet.dgdg.common.exception.ForbiddenException
import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategory
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class ExerciseCategoryTest : FunSpec({

    context("최상위 카테고리를 추가한다") {
        test("depth가 0이하인 카테고리를 생성할 수 없다 throws ForbiddenException") {
            // when & then
            shouldThrowExactly<ForbiddenException> {
                ExerciseCategory(
                    null,
                    "name",
                    "description",
                    0
                )
            }
        }

        test("최상위 카테고리를 생성하면 depth가 1이고 parentCategory가 null인 카테고리가 생성된다") {
            // given
            val englishName = "Shoulder"
            val koreanName = "어깨 운동"

            // when
            val rootCategory = ExerciseCategory.newRootCategory(englishName, koreanName)

            // then
            rootCategory.also {
                it.parentCategory shouldBe null
                it.englishName shouldBe englishName
                it.koreanName shouldBe koreanName
                it.depth shouldBe 1
            }
        }
    }

    context("하위 카테고리를 추가한다") {
        test("하위 카테고리를 추가하면 부모 카테고리보다 depth가 1더 크고 parentCategory가 부모 카테고리를 가리키는 카테고리가 생성된다") {
            // given
            val englishName = "2depth"
            val koreanName = "2뎁스"

            val rootCategory = ExerciseCategory.newRootCategory("1depth", "1depth")

            // when
            rootCategory.addChildCategory(englishName, koreanName)

            // then
            rootCategory.childrenCategories shouldHaveSize 1
            rootCategory.childrenCategories[0].also {
                it.parentCategory shouldBe rootCategory
                it.englishName shouldBe englishName
                it.koreanName shouldBe koreanName
                it.depth shouldBe 2
            }
        }

        test("최대 뎁스인 2를 넘어서 카테고리를 추가 할 수 없다 throws ForbiddenException") {
            // given
            val rootCategory = ExerciseCategory.newRootCategory("1depth", "1depth")
            rootCategory.addChildCategory("2뎁스", "2depth")

            // when & then
            shouldThrowExactly<ForbiddenException> {
                rootCategory.childrenCategories[0].addChildCategory("3뎁스", "3depth")
            }
        }
    }

})
