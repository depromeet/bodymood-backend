package com.depromeet.dgdg.controller;

import com.depromeet.dgdg.controller.dto.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Operation(summary = "Health Check API")
    @GetMapping("/")
    public BaseResponse<String> root() {
        return BaseResponse.HEALTH_CHECK;
    }

}
