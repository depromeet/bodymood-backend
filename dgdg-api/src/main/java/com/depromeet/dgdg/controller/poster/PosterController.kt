package com.depromeet.dgdg.controller.poster

import com.depromeet.dgdg.config.auth.RequiredAuth
import com.depromeet.dgdg.config.auth.UserId
import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.service.poster.PosterService
import com.depromeet.dgdg.service.poster.dto.PosterPhotoResponse
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PosterController(
    private val posterService: PosterService
) {
    @Operation(summary = "포스터 아이디로 사진 조회")
    @RequiredAuth
    @GetMapping("/api/v1/posters/{posterId}")
    fun getPosterPhotoById(@PathVariable posterId: Long, @UserId userId: Long): BaseResponse<PosterPhotoResponse> {
        return BaseResponse.success(posterService.getPosterPhotoById(userId, posterId))
    }

    // TODO : 페이지네이션 추가
    @Operation(summary = "전체 사진 조회")
    @RequiredAuth
    @GetMapping("/api/v1/posters")
    fun getPosterPhotos(@UserId userId: Long): BaseResponse<List<PosterPhotoResponse>> {
        return BaseResponse.success(posterService.getPosterPhotos(userId))
    }
}
