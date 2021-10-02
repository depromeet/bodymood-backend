package com.depromeet.dgdg.common.exception

import com.depromeet.dgdg.common.ErrorCode

/**
 * 503 Service Unavailable
 */
class ServiceUnavailableException(
    override val message: String,
    override val errorCode: ErrorCode = ErrorCode.SERVICE_UNAVAILABLE_EXCEPTION
) : DgDgBaseException(message, errorCode)
