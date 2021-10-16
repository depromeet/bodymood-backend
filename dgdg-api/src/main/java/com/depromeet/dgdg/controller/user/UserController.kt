package com.depromeet.dgdg.controller.user

import com.depromeet.dgdg.config.auth.RequiredAuth
import com.depromeet.dgdg.config.auth.UserId
import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.service.user.UserService
import com.depromeet.dgdg.service.user.dto.request.UserRequest
import com.depromeet.dgdg.service.user.dto.response.UserInfoResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @Operation(summary = "유저의 회원 정보를 조회하는 API", security = [SecurityRequirement(name = "BearerKey")])
    @RequiredAuth
    @GetMapping("/api/v1/user/me")
    fun getMyUserInfo(
        @UserId userId: Long
    ): BaseResponse<UserInfoResponse> {
        return BaseResponse.success(userService.getUserInfo(userId))
    }

    @Operation(summary = "유저의 회원 정보를 수정하는 API", security = [SecurityRequirement(name = "BearerKey")])
    @RequiredAuth
    @PutMapping("/api/v1/user/me")
    fun updateMyUserInfo(
        @RequestBody request: UserRequest,
        @UserId userId: Long
    ): BaseResponse<UserInfoResponse> {
        return BaseResponse.success(userService.updateUserInfo(request, userId))
    }

}
