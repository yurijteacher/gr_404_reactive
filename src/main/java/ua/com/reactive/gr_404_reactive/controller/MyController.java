package ua.com.reactive.gr_404_reactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import ua.com.reactive.gr_404_reactive.entity.User;

@RestController
public class MyController {

    @GetMapping("/get_all")
    public Flux<User> getAll() {
        Flux<User> userFlux = Flux.just(
                new User(1, "Vasya", "Pypkin", 21),
                new User(2, "Iva", "Pypkina", 17),
                new User(3, "Inna", "Pypkina", 20)
        );

        return userFlux;
    }


}
