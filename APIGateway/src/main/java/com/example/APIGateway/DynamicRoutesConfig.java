package com.example.APIGateway;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class SwaggerController {

    private final DiscoveryClient discoveryClient;

    @GetMapping("/v3/api-docs/swagger-config")
    public Map<String, Object> swaggerConfig(ServerHttpRequest request) throws URISyntaxException {
        URI uri = request.getURI();
        String baseUrl = new URI(uri.getScheme(), uri.getAuthority(), null, null, null).toString();

        List<String> serviceNames = discoveryClient.getServices();

        List<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new ArrayList<>();

        for (String serviceName : serviceNames) {
            String serviceUrl = baseUrl + "/" + serviceName + "/v3/api-docs";
            urls.add(new AbstractSwaggerUiConfigProperties.SwaggerUrl(serviceName, serviceUrl, serviceName));
        }

        Map<String, Object> swaggerConfig = new HashMap<>();
        swaggerConfig.put("urls", urls);
        return swaggerConfig;
    }
}