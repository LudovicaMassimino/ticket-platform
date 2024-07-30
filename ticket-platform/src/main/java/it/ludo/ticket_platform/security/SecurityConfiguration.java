package it.ludo.ticket_platform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/ticket/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/ticket/user/**").hasAuthority("OPERATOR")
                .requestMatchers("/operator/**").hasAuthority("OPERATOR")
                .requestMatchers("/ticket/{id}/**").hasAnyAuthority("ADMIN", "OPERATOR")
                .requestMatchers("/css/**", "/js/**", "/webjars/**", "img/**")
                .permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .and().exceptionHandling();

        return http.build();
    }
}
