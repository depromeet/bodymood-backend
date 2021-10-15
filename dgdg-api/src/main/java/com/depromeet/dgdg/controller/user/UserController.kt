package com.depromeet.dgdg.controller.user

import com.depromeet.dgdg.config.auth.RequiredAuth
import com.depromeet.dgdg.config.auth.UserId
import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.service.user.UserService
import com.depromeet.dgdg.service.user.dto.request.UserRequest
import com.depromeet.dgdg.service.user.dto.response.UserInfoResponse
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @ApiOperation("유저의 회원 정보를 조회하는 API")
    @RequiredAuth
    @GetMapping("/api/v1/user/me")
    fun getMyUserInfo(
        @UserId userId: Long
    ): BaseResponse<UserInfoResponse> {
        return BaseResponse.success(userService.getUserInfo(userId))
    }

    @ApiOperation("유저의 회원 정보를 수정하는 API")
    @RequiredAuth
    @PutMapping("/api/v1/user/me")
    fun updateMyUserInfo(
        @RequestBody request: UserRequest,
        @UserId userId: Long
    ): BaseResponse<UserInfoResponse> {
        return BaseResponse.success(userService.updateUserInfo(request, userId))
    }

}
