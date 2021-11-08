package com.depromeet.dgdg.controller.user

import com.depromeet.dgdg.config.auth.RequiredAuth
import com.depromeet.dgdg.config.auth.UserId
import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.service.user.UserService
import com.depromeet.dgdg.service.user.dto.request.UpdateUserInfoRequest
import com.depromeet.dgdg.service.user.dto.response.UserInfoResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
        @Valid @RequestBody request: UpdateUserInfoRequest,
        @UserId userId: Long
    ): BaseResponse<UserInfoResponse> {
        return BaseResponse.success(userService.updateUserInfo(request, userId))
    }

    @Operation(summary = "유저의 회원 정보를 삭제하는 API (회원탈퇴)", security = [SecurityRequirement(name = "BearerKey")])
    @DeleteMapping("/api/v1/user/me")
    @RequiredAuth
    fun deleteUser(@UserId userId: Long): BaseResponse<Nothing>{
        userService.deleteUser(userId)
        return BaseResponse.success(null)
    }

}
