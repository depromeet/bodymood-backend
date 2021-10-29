package com.depromeet.dgdg.controller.poster.dto

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


data class PosterRequest(
    @get:NotNull
    val posterImage: MultipartFile,
    @get:NotNull
    val originImage: MultipartFile,
    @get:NotNull @get:NotBlank
    val emotion: String,
    @get:NotNull @get:NotEmpty
    val categories: List<Long> = mutableListOf()
)


data class PosterDetail(
    @get:NotNull @get:NotBlank
    val emotion: String,
    @get:NotNull @get:NotEmpty
    val categories: List<Long> = mutableListOf()
){
    companion object {
        @JvmStatic
        fun of(emotion: String, categories: List<Long>): PosterDetail {
            return PosterDetail(emotion, categories)
        }
    }
}
