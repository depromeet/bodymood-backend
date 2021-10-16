package com.depromeet.dgdg.service.auth

import com.depromeet.dgdg.common.exception.UnAuthorizedException
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.provider.token.AuthTokenProvider
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload
import com.depromeet.dgdg.service.auth.dto.request.RefreshTokenRequest
import com.depromeet.dgdg.service.user.findUserById
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val authTokenProvider: AuthTokenProvider<AuthTokenPayload>
) {

    @Transactional
    fun logout(userId: Long) {
        val user = findUserById(userRepository, userId)
        user.removeRefreshToken()
    }

    @Transactional(readOnly = true)
    fun refreshAccessToken(request: RefreshTokenRequest): String {
        authTokenProvider.validateRefreshToken(request.refreshToken)
        val user = userRepository.findByRefreshToken(request.refreshToken)
            ?: throw UnAuthorizedException("유효하지 않은 Refresh token (${request.refreshToken}) 입니다")
        return authTokenProvider.createAccessToken(AuthTokenPayload.of(user.id))
    }

}
