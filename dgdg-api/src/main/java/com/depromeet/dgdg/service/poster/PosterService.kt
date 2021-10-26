package com.depromeet.dgdg.service.poster

import com.depromeet.dgdg.common.exception.NotFoundException
import com.depromeet.dgdg.domain.domain.poster.Poster
import com.depromeet.dgdg.domain.domain.poster.PosterRepository
import com.depromeet.dgdg.service.poster.dto.PosterPhotoResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PosterService (
    private val posterRepository: PosterRepository
) {
    @Transactional(readOnly = true)
    fun getPosterPhotoById(userId: Long, posterId: Long): PosterPhotoResponse {
        val poster: Poster = posterRepository.findPosterByIdAndUserId(userId, posterId)
            .orElseThrow { NotFoundException("userId: ${userId}에 해당하는 포스터(posterId: ${posterId})가 존재하지 않습니다") }
        return PosterPhotoResponse.of(poster)
    }

    @Transactional(readOnly = true)
    fun getPosterPhotos(userId: Long): List<PosterPhotoResponse> {
        val posters: List<Poster> = posterRepository.findPostersByUserId(userId)
            .orElseThrow { NotFoundException("${userId}에 해당하는 포스터가 존재하지 않습니다") }
        return posters.map { PosterPhotoResponse.of(it) }
    }
}
