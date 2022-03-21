package com.whh.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {
    /**
     * http://localhost:8080/demo/greeting
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, KeyResolver keyResolver, RedisRateLimiter redisRateLimiter) {
        return builder.routes().route("demo",
                p -> p.path("/demo/**")
                        .filters(gatewayFilterSpec ->
                                gatewayFilterSpec.rewritePath("/demo/(?<path>.*)", "/${path}")
                                        .requestRateLimiter(config -> config.setKeyResolver(keyResolver).setRateLimiter(redisRateLimiter))
                        )
                        .uri("lb://spring-demo-8090")
        )
                .route(p -> p.path("/eureka/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/eureka/(?<path>.*)", "/${path}"))
                        .uri("lb://eureka"))
                .build();
    }

    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getURI().getHost());
    }




}
