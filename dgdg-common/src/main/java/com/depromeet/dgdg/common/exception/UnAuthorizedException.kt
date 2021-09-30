package com.depromeet.dgdg.common.exception

import com.depromeet.dgdg.common.ErrorCode

class UnAuthorizedException(
    override val message: String,
    override val errorCode: ErrorCode = ErrorCode.UNAUTHORIZED_EXCEPTION
) : DgDgBaseException(message, errorCode)
