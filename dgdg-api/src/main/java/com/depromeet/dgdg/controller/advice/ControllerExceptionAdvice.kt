package com.depromeet.dgdg.controller.advice

import com.depromeet.dgdg.common.ErrorCode.*
import com.depromeet.dgdg.common.exception.DgDgBaseException
import com.depromeet.dgdg.common.utils.logger
import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.slf4j.Logger
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.HttpMediaTypeException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingRequestValueException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    protected fun handleBadRequest(e: BindException): BaseResponse<Nothing> {
        log.error(e.message)
        val fieldError = e.fieldError
        return BaseResponse.error(
            BAD_REQUEST_MISSING_REQUIRED_VALUE_EXCEPTION,
            "${fieldError?.field} ${fieldError?.defaultMessage}"
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException::class)
    protected fun handle(e: MissingServletRequestParameterException): BaseResponse<Nothing> {
        log.error(e.message)
        return BaseResponse.error(
            BAD_REQUEST_MISSING_REQUIRED_VALUE_EXCEPTION,
            "${BAD_REQUEST_MISSING_REQUIRED_VALUE_EXCEPTION.message} (${e.parameterName})"
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        MissingKotlinParameterException::class,
        HttpMessageNotReadableException::class,
        MissingRequestValueException::class,
        TypeMismatchException::class,
        InvalidFormatException::class,
        ServletRequestBindingException::class
    )
    private fun handleMethodArgumentNotValidException(e: Exception): BaseResponse<Nothing> {
        log.error(e.message)
        return BaseResponse.error(BAD_REQUEST_EXCEPTION)
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): BaseResponse<Nothing> {
        return BaseResponse.error(METHOD_NOT_ALLOWED_EXCEPTION)
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeException::class)
    protected fun handleHttpMediaTypeException(e: HttpMediaTypeException): BaseResponse<Nothing> {
        return BaseResponse.error(UNSUPPORTED_MEDIA_TYPE)
    }

    @ExceptionHandler(DgDgBaseException::class)
    fun handleInternalServerException(e: DgDgBaseException): ResponseEntity<BaseResponse<Nothing>> {
        log.error(e.message, e)
        return ResponseEntity.status(e.errorCode.status)
            .body(BaseResponse.error(e.errorCode, e.errorCode.message))
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerException(e: Exception): ResponseEntity<BaseResponse<Nothing>> {
        log.error(e.message, e)
        return ResponseEntity.internalServerError()
            .body(BaseResponse.error(INTERNAL_SERVER_EXCEPTION))
    }

    companion object {
        private val log: Logger = logger()
    }

}
