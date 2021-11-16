package com.depromeet.dgdg.controller

import com.depromeet.dgdg.controller.dto.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @Operation(summary = "Health Check")
    @GetMapping("/")
    fun root(): BaseResponse<String> {
        return BaseResponse.OK
    }

}
