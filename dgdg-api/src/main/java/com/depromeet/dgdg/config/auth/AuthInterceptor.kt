package com.depromeet.dgdg.config.auth

import com.depromeet.dgdg.common.exception.UnAuthorizedException
import com.depromeet.dgdg.service.token.AuthTokenProvider
import com.depromeet.dgdg.service.token.dto.AuthTokenPayload
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor(
    private val tokenProvider: AuthTokenProvider<AuthTokenPayload>
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }
        handler.getMethodAnnotation(RequiredAuth::class.java) ?: return true

        val header = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (!(StringUtils.hasLength(header) && header.startsWith(BEARER_PREFIX))) {
            throw UnAuthorizedException("잘못된 토큰입니다. 다시 로그인해주세요. header: ($header)")
        }
        val token = header.split(BEARER_PREFIX)[1]
        val payload = tokenProvider.getPayload(token)

        request.setAttribute(AuthConstants.USER_ID_FIELD, payload.userId)
        return true
    }

    companion object {
        private const val BEARER_PREFIX = "Bearer "
    }

}
