package com.depromeet.dgdg.service.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.depromeet.dgdg.service.auth.dto.response.AuthResponse
import com.depromeet.dgdg.domain.domain.user.SocialProvider
import com.depromeet.dgdg.domain.domain.user.User
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.external.apple.AppleClient
import com.depromeet.dgdg.external.apple.dto.properties.AppleAuthProperties
import com.depromeet.dgdg.provider.token.AuthTokenProvider
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload.Companion.of
import com.depromeet.dgdg.service.auth.dto.request.AppleAuthHeader
import com.depromeet.dgdg.service.auth.dto.request.AuthRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.security.KeyFactory
import java.security.PublicKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.RSAPublicKeySpec
import java.util.*

@Service
class AppleLoginService(
    private val appleLoginValidator: AppleLoginValidator,
    private val userRepository: UserRepository,
    private val jwtAuthTokenProvider: AuthTokenProvider<AuthTokenPayload>
) {
    fun handleAuthentication(request: AuthRequest) : AuthResponse {
        val appleId = appleLoginValidator.getUserIdFromToken(request.accessToken)
        val user: User = userRepository.findBySocialIdAndSocialProvider(appleId, SocialProvider.APPLE)
            .orElseGet { signUpAppleUser(appleId) }
        val accessToken: String = jwtAuthTokenProvider.createAccessToken(of(user.id))
        val refreshToken: String = jwtAuthTokenProvider.createRefreshToken()
        user.updateRefreshToken(refreshToken)
        return AuthResponse.of(accessToken, refreshToken)
    }

    private fun signUpAppleUser(appleId: String): User {
        val newUser = User.newAppleInstance(appleId)
        return userRepository.save(newUser)
    }
}

@Component
class AppleLoginValidator(
    private val appleClient: AppleClient,
    private val appleProperties: AppleAuthProperties,
    private val objectMapper: ObjectMapper
){

    fun getUserIdFromToken(idToken: String) : String {

        val encodedHeader = idToken.split(".").first()
        val decodedHeader = String(Base64.getDecoder().decode(encodedHeader.toByteArray()))
        val header : AppleAuthHeader = objectMapper.readValue(decodedHeader)


        val publicKey = getPublicKey(header);

        val algorithm = Algorithm.RSA256(publicKey as RSAPublicKey, null)
        val verifier: JWTVerifier = JWT.require(algorithm)
            .withIssuer(appleProperties.issuer)
            .withAudience(appleProperties.clientId)
            .build()
        val jwt: DecodedJWT = verifier.verify(idToken)
        return jwt.subject
    }

    private fun getPublicKey(header: AppleAuthHeader) : PublicKey{
        val publicKeys = appleClient.getAppleAuthPublicKey()
        val matchedKey = publicKeys.getMatchedPublicKey(header)

        val n = BigInteger(1, Base64.getUrlDecoder().decode(matchedKey.n))
        val e = BigInteger(1, Base64.getUrlDecoder().decode(matchedKey.e))

        val publicKeySpec = RSAPublicKeySpec(n, e)
        val keyFactory = KeyFactory.getInstance(matchedKey.kty)
        return keyFactory.generatePublic(publicKeySpec)
    }
}
