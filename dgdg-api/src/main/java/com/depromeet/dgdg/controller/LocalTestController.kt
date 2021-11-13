package com.depromeet.dgdg.controller

import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.domain.domain.user.User
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.provider.token.AuthTokenProvider
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload
import com.depromeet.dgdg.service.auth.dto.response.AuthResponse
import io.swagger.v3.oas.annotations.Operation
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Profile("dev", "local", "local-pg")
@RestController
class LocalTestController(
    private val userRepository: UserRepository,
    private val tokenProvider: AuthTokenProvider<AuthTokenPayload>
) {

    @Operation(summary = "[로컬 및 개발용] 테스트용 토큰을 발급 받는 API")
    @GetMapping("/test-token")
    fun getTestToken(): BaseResponse<AuthResponse> {
        val user = userRepository.findActiveUserBySocialIdAndSocialProvider(user.socialId, user.socialProvider)
            ?: user
        userRepository.save(user)

        if (!(user.refreshToken != null && tokenProvider.isValidRefreshToken(user.refreshToken))) {
            user.updateRefreshToken(tokenProvider.createRefreshToken())
            userRepository.save(user)
        }
        val accessToken = tokenProvider.createAccessToken(AuthTokenPayload(user.id))
        return BaseResponse.success(AuthResponse.of(accessToken, user.refreshToken))
    }

    companion object {
        private val user: User = User.newKaKaoInstance("test-social-id", "테스트 계정", "https://profile.com")
    }

}
