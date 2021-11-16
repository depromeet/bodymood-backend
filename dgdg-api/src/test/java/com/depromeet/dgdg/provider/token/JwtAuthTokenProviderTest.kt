package com.depromeet.dgdg.provider.token

import com.depromeet.dgdg.common.exception.UnAuthorizedException
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldStartWith
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class JwtAuthTokenProviderTest(
    private val jwtAuthTokenProvider: JwtAuthTokenProvider
) : FunSpec({

    context("인증 토큰 생성") {
        test("인증 토큰을 생성하면 JWT 토큰이 생성된다") {
            // given
            val userId = 100L

            // when
            val token = jwtAuthTokenProvider.createAccessToken(AuthTokenPayload(userId))

            // then
            with(token) {
                this shouldNotBe null
                this shouldStartWith "ey"
            }
        }
    }

    context("인증 토큰 디코드") {
        test("인증 토큰으로부터 userId가 포함된 Payload를 가져온다") {
            // given
            val userId = 100L
            val token = jwtAuthTokenProvider.createAccessToken(AuthTokenPayload(userId))

            // when
            val authToken = jwtAuthTokenProvider.getPayload(token)

            // then
            authToken.userId shouldBe userId
        }

        test("잘못된 인증 토큰인 경우 UnAuthorized 에러가 발생한다") {
            // given
            val token = "Wrong Token"

            // when & then
            assertThatThrownBy { jwtAuthTokenProvider.getPayload(token) }.isInstanceOf(UnAuthorizedException::class.java)
        }
    }

    context("RefreshToken 생성") {
        test("페이로드가 없는 RefreshToken을 생성한다") {
            // when
            val refreshToken = jwtAuthTokenProvider.createRefreshToken()

            // then
            with(refreshToken) {
                this shouldNotBe null
                this shouldStartWith "ey"
            }
        }
    }

})
