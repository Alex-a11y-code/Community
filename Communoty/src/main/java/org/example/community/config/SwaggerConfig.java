package org.example.community.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

/**
 * Swagger 配置类
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(Environment environment) {
        String serverUrl = "http://47.122.132.101:8080";
        return new OpenAPI()
                .info(new Info()
                        .title("社区的 API 文档")
                        .version("1.0")
                        .description("仿稀土掘金克隆项目的后端 API 文档"))
                .servers(List.of(new Server().url(serverUrl)));
    }
}
