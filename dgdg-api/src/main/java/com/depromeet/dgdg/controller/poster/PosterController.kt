package com.depromeet.dgdg.controller.poster

import com.depromeet.dgdg.config.auth.RequiredAuth
import com.depromeet.dgdg.config.auth.UserId
import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.service.poster.dto.PosterDetail
import com.depromeet.dgdg.service.poster.dto.PosterRequest
import com.depromeet.dgdg.service.poster.dto.PosterResponse
import com.depromeet.dgdg.service.poster.PosterService
import com.depromeet.dgdg.service.poster.dto.PagePosterPhotoResponse
import com.depromeet.dgdg.service.poster.dto.PosterPhotoResponse
import com.depromeet.dgdg.service.upload.S3Service
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class PosterController(
    private val posterService: PosterService,
    private val s3Service: S3Service
) {
    @Operation(summary = "포스터 아이디로 사진 조회", security = [SecurityRequirement(name = "BearerKey")])
    @RequiredAuth
    @GetMapping("/api/v1/posters/{posterId}")
    fun getPosterPhotoById(@PathVariable posterId: Long, @UserId userId: Long): BaseResponse<PosterPhotoResponse> {
        return BaseResponse.success(posterService.getPosterById(userId, posterId))
    }

    @Operation(summary = "전체 포스터 조회", security = [SecurityRequirement(name = "BearerKey")])
    @RequiredAuth
    @GetMapping("/api/v1/posters")
    fun getPosterPhotos(
        @UserId userId: Long,
        @Parameter(description = "페이지", required = true) @RequestParam page: Int,
        @Parameter(description = "개수", required = true) @RequestParam size: Int
    ): BaseResponse<PagePosterPhotoResponse> {
        val pageRequest = PageRequest.of(page, size)
        return BaseResponse.success(posterService.getPosterPhotos(userId, pageRequest))
    }

    @Operation(summary = "포스터 생성", security = [SecurityRequirement(name = "BearerKey")])
    @RequiredAuth
    @PostMapping("/api/v1/posters")
    fun makePoster(
        @UserId userId: Long,
        @Valid @ModelAttribute request: PosterRequest
    ): BaseResponse<PosterResponse> {
        // S3 이미지 저장
        val posterUrl = s3Service.upload(request.posterImage)
        val originUrl = s3Service.upload(request.originImage)

        val detail = PosterDetail.of(request.emotion, request.categories)
        val poster = posterService.makePoster(userId, posterUrl, originUrl, detail)

        return BaseResponse.success(poster)
    }

    @Operation(summary = "포스터 수정", security = [SecurityRequirement(name = "BearerKey")])
    @RequiredAuth
    @PutMapping("/api/v1/posters/{posterId}")
    fun modifyPoster(
        @UserId userId: Long,
        @Valid @ModelAttribute newRequest: PosterRequest,
        @PathVariable posterId: Long
    ): BaseResponse<PosterResponse> {
        val newPosterUrl = s3Service.upload(newRequest.posterImage)
        val originUrl = s3Service.upload(newRequest.originImage)

        val detail = PosterDetail.of(newRequest.emotion, newRequest.categories)

        return BaseResponse.success(
            posterService.modifyPoster(userId, posterId, newPosterUrl, originUrl, detail)
        )

    }

    @Operation(summary = "포스터 삭제", security = [SecurityRequirement(name = "BearerKey")])
    @RequiredAuth
    @DeleteMapping("/api/v1/posters/{posterId}")
    fun deletePoster(@PathVariable posterId: Long, @UserId userId: Long): BaseResponse<String> {
        posterService.deletePoster(userId, posterId)
        return BaseResponse.OK
    }

    @Operation(summary = "포스터 다건 삭제", security = [SecurityRequirement(name = "BearerKey")])
    @RequiredAuth
    @DeleteMapping("/api/v1/posters")
    fun deleteAllUserPosters(@RequestParam posterIds: List<Long>, @UserId userId: Long): BaseResponse<String> {
        posterService.deletePosters(posterIds, userId)
        return BaseResponse.OK
    }

}

