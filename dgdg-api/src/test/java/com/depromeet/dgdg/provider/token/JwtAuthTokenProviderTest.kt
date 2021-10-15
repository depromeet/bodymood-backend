package com.depromeet.dgdg.provider.token

import com.depromeet.dgdg.common.exception.UnAuthorizedException
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
internal class JwtAuthTokenProviderTest(
    private val jwtAuthTokenProvider: JwtAuthTokenProvider
) {

    @Test
    fun 인증_토큰을_생성한다() {
        // given
        val userId = 100L

        // when
        val token = jwtAuthTokenProvider.createAccessToken(AuthTokenPayload(userId))

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
        val token = jwtAuthTokenProvider.createAccessToken(AuthTokenPayload(userId))

        // when
        val authToken = jwtAuthTokenProvider.getPayload(token)

        // then
        assertThat(authToken.userId).isEqualTo(userId)
    }

    @Test
    fun 잘못된_액세스토큰인경우_에러가_발생한다() {
        // given
        val token = "Wrong Token"

        // when & then
        assertThatThrownBy { jwtAuthTokenProvider.getPayload(token) }.isInstanceOf(UnAuthorizedException::class.java)
    }

    @Test
    fun payload가_없는_RefreshToken을_생성한다() {
        // when
        val refreshToken = jwtAuthTokenProvider.createRefreshToken()

        // then
        assertAll({
            assertThat(refreshToken).isNotNull
            assertThat(refreshToken.startsWith("ey")).isTrue
        })
    }

}
