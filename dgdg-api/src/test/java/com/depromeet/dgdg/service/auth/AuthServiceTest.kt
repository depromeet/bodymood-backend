package com.depromeet.dgdg.service.auth

import com.depromeet.dgdg.common.exception.NotFoundException
import com.depromeet.dgdg.common.exception.UnAuthorizedException
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.provider.token.JwtAuthTokenProvider
import com.depromeet.dgdg.service.auth.dto.request.RefreshTokenRequest
import com.depromeet.dgdg.utils.setUpUser
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldStartWith
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class AuthServiceTest(
    private val jwtAuthTokenProvider: JwtAuthTokenProvider,
    private val authService: AuthService,
    private val userRepository: UserRepository
) : FunSpec({

    afterEach {
        userRepository.deleteAll()
    }

    context("로그아웃") {
        test("로그아웃시 유저의 Refresh Token을 삭제한다") {
            // given
            val user = setUpUser()
            user.updateRefreshToken("Refresh-Token")
            userRepository.save(user)

            // when
            authService.logout(user.id)

            // then
            val users = userRepository.findAll()
            users shouldHaveSize 1
            users[0].also {
                it.refreshToken shouldBe null
            }
        }

        test("해당하는 유저가 존재하지 않는 경우 로그아웃 할 수 없다 throws NotFoundExcepton") {
            // when & then
            shouldThrowExactly<NotFoundException> {
                authService.logout(-1L)
            }
        }
    }

    context("액세스 토큰 재발급") {
        test("RefreshToken을 가지고 있는 사용자를 찾아서 액세스 토큰을 재발급한다") {
            // given
            val user = setUpUser()
            val refreshToken = jwtAuthTokenProvider.createRefreshToken()
            user.updateRefreshToken(refreshToken)
            userRepository.save(user)

            // when
            val accessToken = authService.refreshAccessToken(RefreshTokenRequest(refreshToken))

            // then
            accessToken.also {
                it shouldNotBe null
                it shouldStartWith "ey"
            }
        }

        test("유효하지 않은 RefreshToken인 경우 액세스 토큰을 재발급 할 수 없다 throws UnAuthorizedException") {
            // given
            val refreshToken = "Invalid-refresh-token"

            // when & then
            shouldThrowExactly<UnAuthorizedException> {
                authService.refreshAccessToken(RefreshTokenRequest(refreshToken))
            }
        }
    }

})
