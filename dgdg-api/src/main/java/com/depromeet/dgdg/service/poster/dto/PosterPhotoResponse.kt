package com.depromeet.dgdg.service.poster.dto

import com.depromeet.dgdg.domain.domain.poster.Poster
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class PosterPhotoResponse (
    val photoId: Long,
    val imageUrl: String,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val createdAt: LocalDateTime,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val updatedAt: LocalDateTime
){
    companion object {
        fun of(poster: Poster): PosterPhotoResponse {
            return PosterPhotoResponse(
                poster.id,
                poster.imageUrl,
                poster.createdAt,
                poster.updatedAt
            )
        }
    }
}

data class PagePosterPhotoResponse(
    val totalCount: Long,
    val pageTotalCount: Int,
    val pagePosition: Int,
    val posters: List<PosterPhotoResponse>
)
