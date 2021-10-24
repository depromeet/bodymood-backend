package com.depromeet.dgdg.domain.domain.exercise

import com.depromeet.dgdg.common.exception.ForbiddenException
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThatThrownBy

internal class ExerciseCategoryTest : FunSpec({

    context("하위 카테고리를 추가한다") {
        test("하위 카테고리를 추가하면 depth가 1더 큰 카테고리가 생성된다") {
            // given
            val rootCategory = ExerciseCategory.newRootCategory("1depth", "1depth")

            // when
            rootCategory.addChildCategory("2뎁스", "2depth")

            // then
            rootCategory.childrenCategories shouldHaveSize 1
            rootCategory.childrenCategories[0].name shouldBe "2뎁스"
            rootCategory.childrenCategories[0].depth shouldBe 2
        }

        test("최대 뎁스인 2를 넘어서 카테고리를 추가 할 수 없다") {
            // given
            val rootCategory = ExerciseCategory.newRootCategory("1depth", "1depth")
            rootCategory.addChildCategory("2뎁스", "2depth")

            // when & then
            assertThatThrownBy { rootCategory.childrenCategories[0].addChildCategory("3뎁스", "3depth") }
                .isInstanceOf(ForbiddenException::class.java)
        }
    }

})
