package com.depromeet.dgdg.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["com.depromeet.dgdg"])
@Configuration
class FeignClientConfig
