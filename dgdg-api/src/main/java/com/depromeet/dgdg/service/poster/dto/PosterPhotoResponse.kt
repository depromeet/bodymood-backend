package com.depromeet.dgdg.service.poster.dto

import com.depromeet.dgdg.domain.domain.poster.Poster
import java.time.LocalDateTime

data class PosterPhotoResponse (
    val photoId: Long,
    val imageUrl: String,
    val createdAt: LocalDateTime,
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
