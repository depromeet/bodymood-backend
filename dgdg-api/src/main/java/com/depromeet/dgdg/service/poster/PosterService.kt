package com.depromeet.dgdg.service.poster

import com.depromeet.dgdg.common.ErrorCode.NOT_FOUND_EXERCISE_CATEGORY_EXCEPTION
import com.depromeet.dgdg.common.ErrorCode.NOT_FOUND_POSTER_EXCEPTION
import com.depromeet.dgdg.common.exception.NotFoundException
import com.depromeet.dgdg.controller.poster.dto.PosterDetail
import com.depromeet.dgdg.controller.poster.dto.PosterResponse
import com.depromeet.dgdg.domain.domain.exercise.ExerciseCategoryRepository
import com.depromeet.dgdg.domain.domain.poster.Poster
import com.depromeet.dgdg.domain.domain.poster.PosterExerciseCategory
import com.depromeet.dgdg.domain.domain.poster.PosterRepository
import com.depromeet.dgdg.domain.domain.poster.repository.PosterExerciseCategoryRepository
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.service.poster.dto.PagePosterPhotoResponse
import com.depromeet.dgdg.service.poster.dto.PosterPhotoResponse
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PosterService(
    private val posterRepository: PosterRepository,
    private val userRepository: UserRepository,
    private val exerciseCategoryRepository: ExerciseCategoryRepository,
    private val posterExerciseCategoryRepository: PosterExerciseCategoryRepository
) {
    @Transactional(readOnly = true)
    fun getPosterById(userId: Long, posterId: Long): PosterPhotoResponse {
        val poster: Poster = posterRepository.findPosterById(userId, posterId) ?: throw NotFoundException(
            "userId: ${userId}에 해당하는 포스터 (posterId: ${posterId}) 가 존재하지 않습니다",
            NOT_FOUND_POSTER_EXCEPTION
        )
        return PosterPhotoResponse.of(poster)
    }

    @Transactional(readOnly = true)
    fun getPosterPhotos(userId: Long, page: Pageable): PagePosterPhotoResponse {
        val result = posterRepository.findPosters(userId, page)
        val posters = result.content.map { PosterPhotoResponse.of(it) }
        return PagePosterPhotoResponse(result.totalElements, result.totalPages, result.number, posters)
    }

    @Transactional
    fun makePoster(userId: Long, posterUrl: String, originUrl: String, request: PosterDetail): PosterResponse {

        // 포스터 저장
        val user = userRepository.findUserByIdFetchJoinPoster(userId)
        val poster = Poster.of(user, posterUrl, originUrl, request.emotion)
        posterRepository.save(poster)

        // 포스터에 따른 운동 카테고리 저장
        request.categories.map {
            val exerciseCategory = exerciseCategoryRepository.findById(it)
                .orElseGet {
                    throw NotFoundException(
                        "exerciseCategory: ${it}에 해당하는 운동 카테고리가 존재하지 않습니다",
                        NOT_FOUND_EXERCISE_CATEGORY_EXCEPTION
                    )
                }

            posterExerciseCategoryRepository.save(
                PosterExerciseCategory.of(poster, exerciseCategory)
            )
        }

        return PosterResponse.of(poster, request.categories)
    }

    @Transactional
    fun modifyPoster(userId: Long, posterId: Long ,newPosterUrl: String, originUrl: String, newRequest: PosterDetail): PosterResponse? {
        val newPoster = posterRepository.findPosterById(userId, posterId) ?:
        throw NotFoundException("userId: ${userId}에 해당하는 포스터 (posterId: ${posterId}) 가 존재하지 않습니다")
        newPoster.updatePoster(newPosterUrl)
        return newPoster.let { PosterResponse.of(it, newRequest.categories) }
    }

    @Transactional
    fun deletePoster(userId: Long, posterId: Long) {
        val poster : Poster = posterRepository.findPosterById(userId, posterId) ?:
        throw NotFoundException("userId: ${userId}에 해당하는 포스터 (posterId: ${posterId}) 가 존재하지 않습니다")
        return posterRepository.delete(poster)
    }
}
