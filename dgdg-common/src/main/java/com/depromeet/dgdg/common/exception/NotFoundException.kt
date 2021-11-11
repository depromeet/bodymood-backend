package com.depromeet.dgdg.common.exception

import com.depromeet.dgdg.common.ErrorCode

/**
 * 404 NotFound Exception
 */
class NotFoundException(
    override val message: String,
    override val errorCode: ErrorCode = ErrorCode.NOT_FOUND_EXCEPTION
) : DgDgBaseException(message, errorCode)
