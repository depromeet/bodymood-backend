package com.depromeet.dgdg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping({"/", "/ping"})
    public BaseResponse<String> root() {
        return BaseResponse.HEALTH_CHECK;
    }

}
