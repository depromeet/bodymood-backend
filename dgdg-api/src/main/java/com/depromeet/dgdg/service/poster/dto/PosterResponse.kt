package com.depromeet.dgdg.service.poster.dto

import com.depromeet.dgdg.controller.dto.response.BaseTimeResponse
import com.depromeet.dgdg.domain.domain.poster.Poster
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class PosterPhotoResponse(
    val photoId: Long,
    val imageUrl: String,
) : BaseTimeResponse() {
    companion object {
        fun of(poster: Poster): PosterPhotoResponse {
            val response =  PosterPhotoResponse(
                poster.id,
                poster.imageUrl
            )
            response.setBaseTime(poster)
            return response
        }
    }
}

data class PagePosterPhotoResponse(
    val totalCount: Long,
    val pageTotalCount: Int,
    val pagePosition: Int,
    val posters: List<PosterPhotoResponse>
)

data class PosterResponse(
    val id: Long,
    val url: String,
    val emotion: String,
    val categories: List<Long>
) : BaseTimeResponse() {

    companion object {
        fun of(poster: Poster, categories: List<Long>): PosterResponse {
            val response = PosterResponse(poster.id, poster.imageUrl, poster.emotion.name, categories)
            response.setBaseTime(poster)
            return response
        }
    }

}
