package com.depromeet.dgdg.service.token

import com.depromeet.dgdg.common.exception.UnAuthorizedException
import com.depromeet.dgdg.service.token.dto.AuthTokenPayload
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
internal class JwtAuthTokenServiceTest(
    private val jwtAuthTokenService: JwtAuthTokenProvider
) {

    @Test
    fun 인증_토큰을_생성한다() {
        // given
        val userId = 100L

        // when
        val token = jwtAuthTokenService.createAccessToken(AuthTokenPayload(userId))

        // then
        assertAll({
            assertThat(token).isNotNull
            assertThat(token.startsWith("ey")).isTrue
        })
    }

    @Test
    fun 토큰으로부터_Payload를_가져온다() {
        // given
        val userId = 100L
        val token = jwtAuthTokenService.createAccessToken(AuthTokenPayload(userId))

        // when
        val authToken = jwtAuthTokenService.getPayload(token)

        // then
        assertThat(authToken.userId).isEqualTo(userId)
    }

    @Test
    fun 잘못된_액세스토큰인경우_에러가_발생한다() {
        // given
        val token = "Wrong Token"

        // when & then
        assertThatThrownBy { jwtAuthTokenService.getPayload(token) }.isInstanceOf(UnAuthorizedException::class.java)
    }

}
