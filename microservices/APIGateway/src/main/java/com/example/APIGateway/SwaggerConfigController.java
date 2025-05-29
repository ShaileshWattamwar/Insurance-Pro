package com.example.APIGateway;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
public class SwaggerConfigController {

    private final DiscoveryClient discoveryClient;

    public SwaggerConfigController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    private static final List<String> IGNORED_SERVICES = List.of("eureka", "api-gateway");

    @GetMapping("/v3/api-docs/swagger-config")
    public Map<String, Object> swaggerConfig(ServerHttpRequest request) throws URISyntaxException {
        URI uri = request.getURI();
        String baseUrl = new URI(uri.getScheme(), uri.getAuthority(), null, null, null).toString();

        List<SwaggerUrl> urls = new ArrayList<>();
        discoveryClient.getServices().stream()
                .filter(service -> !IGNORED_SERVICES.contains(service))
                .forEach(service -> {
                    String url = baseUrl + "/" + service + "/v3/api-docs";
                    urls.add(new SwaggerUrl(service, url, service));
                });

        Map<String, Object> config = new HashMap<>();
        config.put("urls", urls);
        return config;
    }
}
