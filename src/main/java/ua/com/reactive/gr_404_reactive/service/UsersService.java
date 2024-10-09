package ua.com.reactive.gr_404_reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.gr_404_reactive.entity.Users;
import ua.com.reactive.gr_404_reactive.repository.UserRepository;


@Service
public class UsersService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Flux<Users> findAll() {
        return userRepository.findAll();
    }

    public Mono<Users> findUserById(Long id){
        return userRepository.findById(id);
    }

    public Mono<Users> save(Users user){
        return userRepository
                .save(user)
                .cast(Users.class);
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .cast(UserDetails.class);
    }


}
