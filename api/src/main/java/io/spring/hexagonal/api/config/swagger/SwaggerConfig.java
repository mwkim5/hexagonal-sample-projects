package io.spring.hexagonal.api.config.swagger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    static {
        SpringDocUtils.getConfig()
            .replaceWithClass(LocalDateTime.class, String.class)
            .replaceWithClass(LocalDate.class, String.class)
            .replaceWithClass(LocalTime.class, String.class);
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(apiInfo())
            ;
    }

    private Info apiInfo() {
        return new Info()
            .title("헥사고날 예제 API")
            .description("API 목록")
            .version("1.0.0")
            ;
    }


}
