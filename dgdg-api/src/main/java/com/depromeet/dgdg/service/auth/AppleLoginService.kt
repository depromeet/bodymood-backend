package com.depromeet.dgdg.service.auth

import com.depromeet.dgdg.external.apple.AppleClient
import com.depromeet.dgdg.service.auth.dto.request.AuthRequest
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Service
class AppleLoginService(
    private val appleLoginProvider: AppleLoginProvider
) {
    fun handleAuthentication(request: AuthRequest) : Long {
        val token = appleLoginProvider.getUserIdFromToken(request.getAccessToken())

        return 0;
    }
}

@Component
class AppleLoginProvider(
    private val appleClient: AppleClient
){
    fun getUserIdFromToken(idToken: String) {

        val encodedHeader = idToken.split(".")[0]
        val decodedHeader = Base64.getEncoder().encodeToString(encodedHeader.toByteArray())


        val publicKeys = appleClient.getAppleAuthPublicKey()
        val test = 0;
    }

//    private fun getPublicKey()
}
