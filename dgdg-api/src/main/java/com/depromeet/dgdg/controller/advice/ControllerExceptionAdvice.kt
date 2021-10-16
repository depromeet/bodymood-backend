package com.depromeet.dgdg.controller.advice

import com.depromeet.dgdg.common.ErrorCode.*
import com.depromeet.dgdg.common.exception.DgDgBaseException
import com.depromeet.dgdg.controller.dto.response.BaseResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.HttpMediaTypeException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionAdvice {

    private val log: Logger
        get() = LoggerFactory.getLogger(ControllerExceptionAdvice::class.java)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    protected fun handleBadRequest(e: BindException): BaseResponse<Nothing> {
        log.error(e.message)
        return BaseResponse.error(BAD_REQUEST_EXCEPTION.code, e.bindingResult.fieldError?.defaultMessage)
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(
        HttpRequestMethodNotSupportedException::class
    )
    protected fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): BaseResponse<Nothing> {
        return BaseResponse.error(METHOD_NOT_ALLOWED_EXCEPTION.code, METHOD_NOT_ALLOWED_EXCEPTION.message)
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(
        HttpMediaTypeException::class
    )
    protected fun handleHttpMediaTypeException(e: HttpMediaTypeException): BaseResponse<Nothing> {
        return BaseResponse.error(UNSUPPORTED_MEDIA_TYPE.code, UNSUPPORTED_MEDIA_TYPE.message)
    }

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
