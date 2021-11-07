package com.depromeet.dgdg.domain.domain.poster.repository

import com.depromeet.dgdg.domain.domain.poster.Poster
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PosterRepositoryCustom {
    fun findPosterById(userId: Long, posterId: Long): Poster?
    fun findPosters(userId: Long, pageable: Pageable): Page<Poster>
}
