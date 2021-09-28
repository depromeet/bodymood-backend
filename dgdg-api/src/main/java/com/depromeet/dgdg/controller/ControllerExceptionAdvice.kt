package com.depromeet.dgdg.controller

import com.depromeet.dgdg.common.ErrorCode.*
import com.depromeet.dgdg.common.exception.DgDgBaseException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionAdvice {

    private val log: Logger
        get() = LoggerFactory.getLogger(ControllerExceptionAdvice::class.java)

    @ExceptionHandler(DgDgBaseException::class)
    fun handleInternalServerException(exception: DgDgBaseException): ResponseEntity<BaseResponse<Nothing>> {
        log.error(exception.message, exception)
        return ResponseEntity.status(exception.errorCode.status)
            .body(BaseResponse.error(exception.errorCode.code, exception.errorCode.message))
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerException(exception: Exception): ResponseEntity<BaseResponse<Nothing>> {
        log.error(exception.message, exception)
        return ResponseEntity.internalServerError()
            .body(BaseResponse.error(INTERNAL_SERVER_EXCEPTION.code, INTERNAL_SERVER_EXCEPTION.message))
    }

}
