package com.depromeet.dgdg.common.exception

import com.depromeet.dgdg.common.ErrorCode

/**
 * 409 Conflict Exception
 */
class ConflictException(
    override val message: String,
    override val errorCode: ErrorCode = ErrorCode.CONFLICT_EXCEPTION
) : DgDgBaseException(message, errorCode)
