package ua.com.reactive.gr_404_reactive.entity;

import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.netty.http.server.HttpServer;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Flux<User> userFlux = Flux.just(
                new User(1, "Vasya", "Pypkin", 21),
                new User(2, "Ivanna", "Pypkina", 18),
                new User(3, "Iva", "Pypkina", 22)
        );

        HandlerFunction<ServerResponse> helloUsers = request -> {
            return ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(userFlux, User.class);
        };

        RouterFunction router = RouterFunctions
                .route(RequestPredicates.GET("/users") ,helloUsers);

        HttpHandler handler = RouterFunctions.toHttpHandler(router);

        HttpServer
                .create()
                .port(8080)
                .handle(new ReactorHttpHandlerAdapter(handler))
                .bindNow();

        Thread.currentThread().join();
    }
}
