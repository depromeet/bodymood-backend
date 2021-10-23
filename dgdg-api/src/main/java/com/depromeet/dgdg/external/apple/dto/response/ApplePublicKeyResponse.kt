package com.depromeet.dgdg.external.apple.dto.response

import com.depromeet.dgdg.service.auth.dto.request.AppleAuthHeader

data class ApplePublicKeyResponse(
    val keys : List<ApplePublicKey>
) {
    fun getMatchedPublicKey(header: AppleAuthHeader): ApplePublicKey {
        return keys.first { key ->
            (key.kid == header.kid && key.alg == header.alg)
        }
    }
}

data class ApplePublicKey(
    val kty: String,
    val kid: String,
    val use: String,
    val alg: String,
    val n: String,
    val e: String
)
