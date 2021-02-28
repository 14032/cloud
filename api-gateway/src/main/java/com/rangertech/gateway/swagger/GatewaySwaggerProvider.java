package com.rangertech.gateway.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Primary
public class GatewaySwaggerProvider implements SwaggerResourcesProvider {

    public static final String API_URI = "/v2/api-docs";
    private final RouteLocator routeLocator;

    @Value("${swagger.enable}")
    private Boolean enable;

    public GatewaySwaggerProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        if (enable) {
            List<Route> routes = new ArrayList<>();
            routeLocator.getRoutes().filter(route -> route.getId() != null)
                    .filter(route -> !route.getId().contains("Discovery")).subscribe(routes::add);
            Set<String> routeSet = new HashSet<>();

            routes.forEach(route -> {
                String predicates = route.getUri().toString();
                String url = predicates.substring(predicates.lastIndexOf("//") + 1) + API_URI;
                if (!routeSet.contains(url)) {
                    routeSet.add(url);
                    SwaggerResource swaggerResource = new SwaggerResource();
                    swaggerResource.setUrl(url);
                    swaggerResource.setName(route.getId());
                    swaggerResource.setSwaggerVersion("1.0");
                    resources.add(swaggerResource);
                }
            });

        }
        return resources;
    }
}