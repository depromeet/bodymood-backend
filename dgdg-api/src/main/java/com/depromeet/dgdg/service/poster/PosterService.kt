package com.depromeet.dgdg.service.poster

import com.depromeet.dgdg.common.exception.NotFoundException
import com.depromeet.dgdg.controller.poster.dto.PosterDetail
import com.depromeet.dgdg.controller.poster.dto.PosterResponse
import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategoryRepository
import com.depromeet.dgdg.domain.domain.poster.Poster
import com.depromeet.dgdg.domain.domain.poster.PosterExerciseCategory
import com.depromeet.dgdg.domain.domain.poster.PosterRepository
import com.depromeet.dgdg.domain.domain.poster.repository.PosterExerciseCategoryRepository
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.service.poster.dto.PosterPhotoResponse
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PosterService (
    private val posterRepository: PosterRepository,
    private val userRepository: UserRepository,
    private val exerciseCategoryRepository: ExerciseCategoryRepository,
    private val posterExerciseCategoryRepository: PosterExerciseCategoryRepository
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

    @Transactional
    fun makePoster(userId: Long, posterUrl: String, originUrl: String, request: PosterDetail) : PosterResponse {

        // 포스터 저장
        val user = userRepository.findUserByIdFetchJoinPoster(userId)
        val poster = Poster.of(user, posterUrl, originUrl, request.emotion)
        posterRepository.save(poster)

        // 포스터에 따른 운동 카테고리 저장
        request.categories.map {
            val exerciseCategory = exerciseCategoryRepository.findById(it)
                .orElseGet { throw NotFoundException("exerciseCategory: ${it}에 해당하는 운동 카테고리가 존재하지 않습니다")  }

            posterExerciseCategoryRepository.save(
                PosterExerciseCategory.of(poster, exerciseCategory)
            )
        }

        return PosterResponse.of(poster, request.categories)
    }
}
