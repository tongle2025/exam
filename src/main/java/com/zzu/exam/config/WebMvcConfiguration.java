package com.zzu.exam.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class WebMvcConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        log.info("开始创建knife4j接口文档...");
        return new OpenAPI()
                .info(new Info()
                        .title("exam doc")
                        .description("description")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("tongle")
                                .email("2541150864@qq.com")));
    }


}
