package com.depromeet.dgdg.common.exception

import com.depromeet.dgdg.common.ErrorCode

class TokenExpiredException(
    override val message: String,
    override val errorCode: ErrorCode = ErrorCode.TOKEN_EXPIRED_EXCEPTION
) : DgDgBaseException(message, errorCode)
