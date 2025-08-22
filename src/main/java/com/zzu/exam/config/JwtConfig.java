package com.zzu.exam.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "exam.jwt")
@Data
public class JwtConfig {

    private String UserSecretKey;
    private long UserTtl;
    private String UserTokenName;

}
