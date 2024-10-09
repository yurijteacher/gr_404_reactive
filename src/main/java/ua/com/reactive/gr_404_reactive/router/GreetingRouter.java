package ua.com.reactive.gr_404_reactive.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import ua.com.reactive.gr_404_reactive.entity.User;
import ua.com.reactive.gr_404_reactive.handler.GreetingHandler;

import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        RequestPredicate accept = accept(MediaType.APPLICATION_JSON);

        return RouterFunctions
                .route(RequestPredicates.GET("/hello").and(accept), greetingHandler::hello)
                .andRoute(RequestPredicates.GET("/"), greetingHandler::home)
                .andRoute(RequestPredicates.GET("/users"), greetingHandler::users)
                .andRoute(RequestPredicates.GET("/user"), greetingHandler::user)
                .andRoute(RequestPredicates.GET("/registration"), greetingHandler::registration);
    }
}