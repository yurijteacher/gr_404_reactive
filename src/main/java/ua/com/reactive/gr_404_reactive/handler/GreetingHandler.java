package ua.com.reactive.gr_404_reactive.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.gr_404_reactive.entity.Greeting;
import ua.com.reactive.gr_404_reactive.entity.User;
import ua.com.reactive.gr_404_reactive.entity.Users;
import ua.com.reactive.gr_404_reactive.repository.UserRepository;
import ua.com.reactive.gr_404_reactive.service.UsersService;

import java.util.Map;

@Component
public class GreetingHandler {


    private final UsersService usersService;

    @Autowired
    public GreetingHandler(UsersService usersService) {
        this.usersService = usersService;
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hello, Spring!")));
    }

    public Mono<ServerResponse> users(ServerRequest request) {

        Flux<User> userFlux = Flux.just(
                new User(1, "Vasya", "Pypkin", 21),
                new User(2, "Iva", "Pypkina", 17),
                new User(3, "Inna", "Pypkina", 20)
        );

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userFlux, User.class);
    }

    public Mono<ServerResponse> home(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("This is a Main Page!"));
    }

    public Mono<ServerResponse> user(ServerRequest request) {

        String user = request.queryParam("user").orElse("no");

        return ServerResponse
                .ok()
                .render("index", Map.of("user", user));
    }


    public Mono<ServerResponse> registration(ServerRequest request) {
        return ServerResponse.ok().render("registration");
    }


}
