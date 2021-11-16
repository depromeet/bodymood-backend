package com.depromeet.dgdg.controller.dto.response

import com.depromeet.dgdg.common.ErrorCode

data class BaseResponse<T>(
    val code: String,
    val message: String,
    val data: T?
) {

    companion object {
        @JvmField
        val OK = success("OK")

        @JvmStatic
        fun <T> success(data: T): BaseResponse<T> {
            return BaseResponse("0000", "성공", data)
        }

        fun error(errorCode: ErrorCode): BaseResponse<Nothing> {
            return BaseResponse(errorCode.code, errorCode.message, null)
        }

        fun error(errorCode: ErrorCode, message: String): BaseResponse<Nothing> {
            return BaseResponse(errorCode.code, message, null)
        }
    }

}
