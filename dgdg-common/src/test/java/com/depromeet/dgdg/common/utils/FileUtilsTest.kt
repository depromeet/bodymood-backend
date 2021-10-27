package com.depromeet.dgdg.common.utils

import com.depromeet.dgdg.common.exception.BadRequestException
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldEndWith
import org.assertj.core.api.Assertions.assertThatThrownBy

internal class FileUtilsTest : FunSpec({

    context("createUniqueFileNameWithExtension") {
        test("기존의 확장자를 유지한채 유니크한 파일 이름을 생성한다") {
            // given
            val originalFileName = "image.png"

            // when
            val result = FileUtils.createUniqueFileNameWithExtension(originalFileName)

            // then
            result shouldEndWith ".png"
        }

        test("확장자가 없는 경우 BadRequestException") {
            // given
            val originalFileName = "image"

            // when & then
            assertThatThrownBy { FileUtils.createUniqueFileNameWithExtension(originalFileName) }
                .isInstanceOf(BadRequestException::class.java)
        }

        test("파일 이름이 null이 넘어와도 BadRequestException") {
            // given
            val originalFileName = null

            // when & then
            assertThatThrownBy { FileUtils.createUniqueFileNameWithExtension(originalFileName) }
                .isInstanceOf(BadRequestException::class.java)
        }
    }

})
