package com.depromeet.dgdg.common.exception

import com.depromeet.dgdg.common.ErrorCode

/**
 * 403 Forbidden Exception
 */
class ForbiddenException(
    override val message: String,
    override val errorCode: ErrorCode = ErrorCode.FORBIDDEN_EXCEPTION
) : DgDgBaseException(message, errorCode)
