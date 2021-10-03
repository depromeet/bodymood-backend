package com.depromeet.dgdg.common.exception

import com.depromeet.dgdg.common.ErrorCode

/**
 * 401 UnAuthorized Exception
 */
open class UnAuthorizedException(
    override val message: String,
    override val errorCode: ErrorCode = ErrorCode.UNAUTHORIZED_EXCEPTION
) : DgDgBaseException(message, errorCode)


/**
 * 만료된 토큰인 경우 발생하는 Exception
 */
class JwtTokenExpiredException(
    override val message: String,
    override val errorCode: ErrorCode = ErrorCode.TOKEN_EXPIRED_EXCEPTION
) : UnAuthorizedException(message, errorCode)
