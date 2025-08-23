package com.zzu.exam.config;

import com.zzu.exam.interceptor.JwtTokenInterceptor;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;


    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/login",
                        "/doc.html/**",
                        "/webjars/**",
                        "/v3/api-docs/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**");
    }

    @Bean
    public OpenAPI customOpenAPI() {
        log.info("开始创建knife4j接口文档... hashCode: {}", this.hashCode());
        return new OpenAPI()
                .info(new Info()
                        .title("exam doc")
                        .description("description")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("tongle")
                                .email("2541150864@qq.com")));
    }

    // // 在配置类中添加资源映射
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     log.info("开始添加静态资源映射...");
    //     registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
    //     registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }
}
