package com.depromeet.dgdg.domain.domain.poster

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PosterRepository : JpaRepository<Poster, Long> {
    fun findPosterByIdAndUserId(userId: Long, id: Long): Optional<Poster>
    fun findPostersByUserId(userId: Long): Optional<List<Poster>>
}
