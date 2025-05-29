package com.example.APIGateway;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DynamicRoutesConfig {

    private final DiscoveryClient discoveryClient;

    public DynamicRoutesConfig(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Bean
    public RouteLocator dynamicRoutes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routesBuilder = builder.routes();

        discoveryClient.getServices().forEach(serviceId -> {
            routesBuilder.route(serviceId,
                    r -> r.path("/" + serviceId + "/**")
                            .filters(f -> f.stripPrefix(1))
                            .uri("lb://" + serviceId)
            );
        });

        return routesBuilder.build();
    }
}