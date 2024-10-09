package ua.com.reactive.gr_404_reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//            return     NoOpPasswordEncoder.getInstance();
    }



    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(
                        exchange ->
                                exchange.pathMatchers("/", "/hello", "/registration")
                                        .permitAll()
                                        .pathMatchers("/all")
                                            .hasRole("USER")
                                        .pathMatchers("/get_all")
                                            .hasRole("ADMIN")
                                        .anyExchange()
                                        .authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }

}
