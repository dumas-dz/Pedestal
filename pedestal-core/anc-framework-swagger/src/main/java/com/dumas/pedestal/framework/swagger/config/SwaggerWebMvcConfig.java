package com.dumas.pedestal.framework.swagger.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc OpenAPI 自动处理静态资源映射，无需手动配置
 * 保留此类仅为兼容条件装配
 *
 * @author dumas
 * @date 2021/12/06 2:17 PM
 */
@Configuration
@ConditionalOnProperty(name = "doc.enabled", prefix = "swagger", havingValue = "true", matchIfMissing = true)
public class SwaggerWebMvcConfig {
}
