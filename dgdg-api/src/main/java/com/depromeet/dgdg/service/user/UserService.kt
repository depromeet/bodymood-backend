package com.depromeet.dgdg.service.user

import com.depromeet.dgdg.common.exception.NotFoundException
import com.depromeet.dgdg.domain.domain.user.User
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.service.user.dto.request.UserRequest
import com.depromeet.dgdg.service.user.dto.response.UserInfoResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun getUserInfo(userId: Long): UserInfoResponse {
        val user = findUserById(userRepository, userId)
        return UserInfoResponse.of(user)
    }

    @Transactional
    fun updateUserInfo(request: UserRequest, userId: Long): UserInfoResponse {
        val user = findUserById(userRepository, userId)
        user.updateInfo(request.name, request.profileUrl)
        return UserInfoResponse.of(user)
    }

}

fun findUserById(userRepository: UserRepository, userId: Long): User {
    return userRepository.findUserById(userId)
        ?: throw NotFoundException("해당하는 유저 ($userId)는 존재하지 않습니다")
}
