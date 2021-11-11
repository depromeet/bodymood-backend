package com.depromeet.dgdg.config.swagger

import com.depromeet.dgdg.config.auth.UserId
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.SpringDocUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("local", "local-pg", "dev")
@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes(
                        securityKey,
                        SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")
                    )
            )
            .info(
                Info()
                    .title("Bodymood API Docs")
                    .description("Bodymood API Documents")
            )
    }

    companion object {
        private const val securityKey = "BearerKey"

        init {
            SpringDocUtils.getConfig().addAnnotationsToIgnore(UserId::class.java)
        }
    }

}
