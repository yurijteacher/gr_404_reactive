package ua.com.reactive.gr_404_reactive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.gr_404_reactive.entity.RoleUser;
import ua.com.reactive.gr_404_reactive.entity.Users;
import ua.com.reactive.gr_404_reactive.service.UsersService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UsersService userService;

    @GetMapping("/all")
    public Flux<Users> getAll() {
        return userService.findAll();
    }

//    @PostMapping("/all")
//    public Mono<Users> createUser(@RequestBody Users user) {
//        return userService.save(user);
//    }

    @PostMapping("/registration")
    public Mono<Users> registerUser(@RequestBody Users user) {

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(RoleUser.ROLE_USER);

        return userService.save(user);
    }


}
