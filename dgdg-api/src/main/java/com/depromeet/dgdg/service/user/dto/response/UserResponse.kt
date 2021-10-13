package com.depromeet.dgdg.service.user.dto.response

import com.depromeet.dgdg.domain.domain.user.SocialProvider
import com.depromeet.dgdg.domain.domain.user.User

data class UserInfoResponse(
    val socialProvider: SocialProvider,
    val name: String,
    val profileUrl: String?
) {

    companion object {
        fun of(user: User): UserInfoResponse {
            return UserInfoResponse(user.socialProvider, user.name, user.profileUrl)
        }
    }

}
