package com.depromeet.dgdg.service.user

import com.depromeet.dgdg.common.ErrorCode.NOT_FOUND_USER_EXCEPTION
import com.depromeet.dgdg.common.exception.NotFoundException
import com.depromeet.dgdg.domain.domain.user.User
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.service.user.dto.request.UpdateUserInfoRequest
import com.depromeet.dgdg.service.user.dto.response.UserInfoResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun getUserInfo(userId: Long): UserInfoResponse {
        val user = findActiveUserById(userRepository, userId)
        return UserInfoResponse.of(user)
    }

    @Transactional
    fun updateUserInfo(request: UpdateUserInfoRequest, userId: Long): UserInfoResponse {
        val user = findActiveUserById(userRepository, userId)
        user.updateInfo(request.name, request.profileUrl)
        return UserInfoResponse.of(user)
    }

    @Transactional
    fun deleteUser(userId: Long) {
        // user 삭제
        val user = findActiveUserById(userRepository, userId)
        user.delete()

        // user에 매핑된 포스터 삭제
        user.posters.forEach{ poster -> poster.delete() }
    }

}

fun findActiveUserById(userRepository: UserRepository, userId: Long): User {
    return userRepository.findActiveUsersById(userId)
        ?: throw NotFoundException("해당하는 유저 ($userId)는 존재하지 않습니다", NOT_FOUND_USER_EXCEPTION)
}
