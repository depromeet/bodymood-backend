package com.depromeet.dgdg.common.exception

import com.depromeet.dgdg.common.ErrorCode
import java.lang.RuntimeException

abstract class DgDgBaseException(
    override val message: String,
    open val errorCode: ErrorCode
) : RuntimeException(message)
