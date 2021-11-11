package com.depromeet.dgdg.controller.dto.response

import com.depromeet.dgdg.domain.domain.BaseTimeEntity
import java.time.LocalDateTime

open class BaseTimeResponse(
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) {

    fun setBaseTime(entity: BaseTimeEntity) {
        this.createdAt = entity.createdAt
        this.updatedAt = entity.updatedAt
    }

}
