package com.depromeet.dgdg.service.auth

import com.depromeet.dgdg.common.exception.UnAuthorizedException
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.provider.token.JwtAuthTokenProvider
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload
import com.depromeet.dgdg.service.auth.dto.request.RefreshTokenRequest
import com.depromeet.dgdg.service.user.findActiveUserById
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtAuthTokenProvider: JwtAuthTokenProvider
) {

    @Transactional
    fun logout(userId: Long) {
        val user = findActiveUserById(userRepository, userId)
        user.removeRefreshToken()
    }

    @Transactional(readOnly = true)
    fun refreshAccessToken(request: RefreshTokenRequest): String {
        jwtAuthTokenProvider.validateRefreshToken(request.refreshToken)
        val user = userRepository.findByRefreshToken(request.refreshToken)
            ?: throw UnAuthorizedException("유효하지 않은 Refresh token (${request.refreshToken}) 입니다")
        return jwtAuthTokenProvider.createAccessToken(AuthTokenPayload.of(user.id))
    }

}
