package com.depromeet.dgdg.common.exception

import com.depromeet.dgdg.common.ErrorCode

/**
 * 400 BadRequest
 */
class BadRequestException(
    override val message: String,
    override val errorCode: ErrorCode = ErrorCode.BAD_REQUEST_EXCEPTION
) : DgDgBaseException(message, errorCode)
