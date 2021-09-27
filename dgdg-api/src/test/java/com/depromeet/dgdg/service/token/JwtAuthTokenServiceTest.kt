package com.depromeet.dgdg.service.token

import com.depromeet.dgdg.service.token.dto.AuthTokenPayload
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class JwtAuthTokenServiceTest(
    @Autowired
    private val jwtAuthTokenService: JwtAuthTokenService
) {

    @Test
    fun 인증_토큰을_생성한다() {
        // given
        val memberId = 100L

        // when
        val token = jwtAuthTokenService.createAccessToken(AuthTokenPayload(memberId))

        // then
        assertThat(token).isNotNull
        assertThat(token.startsWith("ey")).isTrue
    }

    @Test
    fun 토큰으로부터_Payload를_가져온다() {
        // given
        val memberId = 100L
        val token = jwtAuthTokenService.createAccessToken(AuthTokenPayload(memberId))

        // when
        val authToken = jwtAuthTokenService.getPayload(token)

        // then
        assertThat(authToken.memberId).isEqualTo(memberId)
    }

    @Test
    fun 잘못된_액세스토큰인경우_에러가_발생한다() {
        // given
        val token = "Wrong Token"

        // when & then
        assertThatThrownBy { jwtAuthTokenService.getPayload(token) }.isInstanceOf(IllegalArgumentException::class.java)
    }

}
