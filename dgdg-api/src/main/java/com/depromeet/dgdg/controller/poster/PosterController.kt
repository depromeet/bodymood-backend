package com.depromeet.dgdg.controller.poster

import com.depromeet.dgdg.config.auth.RequiredAuth
import com.depromeet.dgdg.config.auth.UserId
import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.service.poster.PosterService
import com.depromeet.dgdg.service.poster.dto.PosterPhotoResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
class PosterController(
    private val posterService: PosterService
) {
    @Operation(summary = "포스터 아이디로 사진 조회")
    @RequiredAuth
    @GetMapping("/api/v1/posters/{posterId}")
    fun getPosterPhotoById(@PathVariable posterId: Long, @UserId userId: Long): BaseResponse<PosterPhotoResponse> {
        return BaseResponse.success(posterService.getPosterById(userId, posterId))
    }


    @Operation(summary = "전체 포스터 조회")
    @RequiredAuth
    @GetMapping("/api/v1/posters")
    fun getPosterPhotos(
        @UserId userId: Long,
        @Parameter(description = "페이지", required = true) @RequestParam page: Int,
        @Parameter(description = "개수", required = true) @RequestParam size: Int
    ): BaseResponse<List<PosterPhotoResponse>> {
        val pageRequest = PageRequest.of(page, size)
        return BaseResponse.success(posterService.getPosterPhotos(userId, pageRequest))
    }
}
