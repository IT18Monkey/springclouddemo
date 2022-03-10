package com.whh.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {
    /**
     * http://localhost:8080/demo/greeting
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes().route(
                p -> p.path("/demo/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/demo/(?<segment>.*)","/$\\{segment}"))
                        .uri("lb://spring-demo-8090")
        ).build();
    }
}
