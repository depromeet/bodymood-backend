package com.depromeet.dgdg.service.poster.dto

import com.depromeet.dgdg.domain.domain.poster.Poster

data class PosterPhotoResponse (
    val photoId: Long,
    val imageUrl: String
){
    companion object {
        fun of(poster: Poster): PosterPhotoResponse {
            return PosterPhotoResponse(
                poster.id,
                poster.imageUrl
            )
        }
    }
}
