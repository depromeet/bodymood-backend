package com.depromeet.dgdg.domain.domain.common

import java.util.*
import javax.persistence.Embeddable

@Embeddable
class Uuid private constructor(
    var uuid: String
) {

    companion object {
        private const val VERSION = "v1"

        fun newInstance(): Uuid {
            return Uuid("${UUID.randomUUID()}-$VERSION")
        }
    }

}
