package com.depromeet.dgdg.controller

import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.domain.domain.user.User
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.provider.token.AuthTokenProvider
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Profile("dev", "local")
@RestController
class LocalTestController(
    private val userRepository: UserRepository,
    private val tokenProvider: AuthTokenProvider<AuthTokenPayload>
) {

    @GetMapping("/test-token")
    fun getTestToken(): BaseResponse<String> {
        val user = userRepository.findBySocialIdAndSocialProvider(user.socialId, user.socialProvider)
            .orElseGet { user }
        userRepository.save(user)
        return BaseResponse.success(tokenProvider.createAccessToken(AuthTokenPayload(user.id)))
    }

    companion object {
        val user: User = User.newKaKaoInstance("test-social-id", "테스트 계정", "https://profile.com")
    }

}
