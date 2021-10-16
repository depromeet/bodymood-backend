package com.depromeet.dgdg.external.apple.dto.response


data class ApplePublicKeyResponse(
    val keys : List<ApplePublicKey>
){
//    fun getMatchedPublicKey(header: String) : ApplePublicKey {
//        keys.filter {
//            key -> key.
//        }
//    }
}


data class ApplePublicKey(
    val kty: String,
    val kid: String,
    val use: String,
    val alg: String,
    val n: String,
    val e: String
)
