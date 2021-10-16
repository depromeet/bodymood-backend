package com.depromeet.dgdg.external.apple

import com.depromeet.dgdg.external.apple.dto.response.ApplePublicKeyResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping


@FeignClient(name = "apple-client", url = "https://appleid.apple.com/auth")
interface AppleClient {

    @GetMapping("/keys")
    fun getAppleAuthPublicKey(): ApplePublicKeyResponse?
}
