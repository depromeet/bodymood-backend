package com.depromeet.dgdg.service.poster

import com.depromeet.dgdg.common.exception.NotFoundException
import com.depromeet.dgdg.domain.domain.poster.Poster
import com.depromeet.dgdg.domain.domain.poster.PosterRepository
import com.depromeet.dgdg.service.poster.dto.PosterPhotoResponse
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PosterService (
    private val posterRepository: PosterRepository
) {
    @Transactional(readOnly = true)
    fun getPosterById(userId: Long, posterId: Long): PosterPhotoResponse {
        val poster : Poster = posterRepository.findPosterById(userId, posterId) ?:
            throw NotFoundException("userId: ${userId}에 해당하는 포스터 (posterId: ${posterId}) 가 존재하지 않습니다")
        return PosterPhotoResponse.of(poster)
    }

    @Transactional(readOnly = true)
    fun getPosterPhotos(userId: Long, page: Pageable): List<PosterPhotoResponse> {
        val posters: List<Poster> = posterRepository.findPosters(userId, page)
        if(posters.isEmpty())
            throw NotFoundException("${userId}에 해당하는 포스터가 존재하지 않습니다")

        return posters.map { PosterPhotoResponse.of(it) }
    }
}
