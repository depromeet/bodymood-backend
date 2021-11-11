package com.depromeet.dgdg.domain.domain.user

object UserCreator {

    fun create(socialId: String, socialProvider: SocialProvider, name: String, profileUrl: String): User {
        return User(socialId, socialProvider, name, profileUrl)
    }

}
