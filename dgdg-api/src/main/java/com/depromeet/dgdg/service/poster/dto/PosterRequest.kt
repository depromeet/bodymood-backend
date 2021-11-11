package com.depromeet.dgdg.service.poster.dto

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


data class PosterRequest(
    @field:NotNull
    val posterImage: MultipartFile?,
    @field:NotNull
    val originImage: MultipartFile?,
    @field:NotBlank
    val emotion: String = "",
    @field:NotEmpty
    val categories: List<Long> = mutableListOf()
)


data class PosterDetail(
    @field:NotBlank
    val emotion: String = "",
    @field:NotEmpty
    val categories: List<Long> = mutableListOf()
){
    companion object {
        @JvmStatic
        fun of(emotion: String, categories: List<Long>): PosterDetail {
            return PosterDetail(emotion, categories)
        }
    }
}
