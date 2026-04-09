package com.dumas.pedestal.framework.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * SpringDoc OpenAPI 配置 (替代已停止维护的 Springfox)
 *
 * @author dumas
 * @date 2021/12/06 2:45 PM
 */
@Configuration
@ConditionalOnProperty(name = "doc.enabled", prefix = "swagger", havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {

    @Value("${swagger.doc.title:标题}")
    private String docTitle;

    @Value("${swagger.doc.desc:接口描述}")
    private String docDesc;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(docTitle)
                        .description(docDesc)
                        .version("1.0"))
                .servers(List.of(
                        new Server().url("/")
                ));
    }
}
