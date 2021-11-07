package com.depromeet.dgdg.controller.poster.dto

import com.depromeet.dgdg.domain.domain.poster.Poster

data class PosterResponse(
    val id: Long,
    val url: String,
    val emotion: String,
    val categories: List<Long>
){
    companion object{
        fun of(poster: Poster, categories: List<Long>): PosterResponse {
            return PosterResponse(poster.id, poster.imageUrl, poster.emotion.name, categories)
        }
    }
}
