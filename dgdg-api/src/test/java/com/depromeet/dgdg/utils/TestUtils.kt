package com.depromeet.dgdg.utils

import com.depromeet.dgdg.domain.domain.user.SocialProvider
import com.depromeet.dgdg.domain.domain.user.User
import com.depromeet.dgdg.domain.domain.user.UserCreator

fun setUpUser(): User {
    return UserCreator.create(
        socialId = "social-id",
        socialProvider = SocialProvider.KAKAO,
        name = "득근득근",
        profileUrl = "https://dgdg.profile.com"
    )
}
