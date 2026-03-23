package com.capputeeno.api_gateway;

import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GlobalFilterLog implements GlobalFilter {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalFilterLog.class);

    private static final String AUTH_HEADER = "Authorization";

    private static final String[] PROTECTED_PATHS = {
            "/api/v1/orders"
    };

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("GlobalFilterLog: Request URL: {}", exchange.getRequest().getURI());

        boolean isAuthenticated = exchange.getRequest().getHeaders().containsHeader(AUTH_HEADER);

        String path = exchange.getRequest().getURI().getPath();

        if (isProtectedPath(path) && !isAuthenticated) {
            logger.warn("GlobalFilterLog: Unauthorized access attempt to protected path: {}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);

    }

    private boolean isProtectedPath(String path) {
        for (String protectedPath : PROTECTED_PATHS) {
            if (path.startsWith(protectedPath)) {
                return true;
            }
        }
        return false;
    }

}
