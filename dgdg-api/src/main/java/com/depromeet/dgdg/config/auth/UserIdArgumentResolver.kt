package com.depromeet.dgdg.config.auth

import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserIdArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(UserId::class.java) != null
            && parameter.parameterType == Long::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        if (parameter.getMethodAnnotation(RequiredAuth::class.java) == null) {
            throw IllegalArgumentException("인증이 필요한 컨트롤러에 @RequiredAuth 어노테이션을 추가해주세요")
        }

        return webRequest.getAttribute(AuthConstants.USER_ID_FIELD, 0)
    }

}
