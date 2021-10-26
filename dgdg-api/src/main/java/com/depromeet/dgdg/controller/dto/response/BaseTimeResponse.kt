package com.depromeet.dgdg.controller.dto.response

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import java.time.LocalDateTime

open class BaseTimeResponse(
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) {

    fun setBaseTime(entity: BaseTimeEntity): BaseTimeResponse {
        return BaseTimeResponse(entity.createdAt, entity.updatedAt)
    }

}
