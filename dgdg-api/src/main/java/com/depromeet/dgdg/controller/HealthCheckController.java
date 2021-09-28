package com.depromeet.dgdg.controller;

import com.depromeet.dgdg.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping({"/", "/ping"})
    public BaseResponse<String> root() {
        return BaseResponse.HEALTH_CHECK;
    }

}
