package com.example.LearnHub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // ----- PUBLIC ENDPOINTS -----
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/courses/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/lessons/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/comments/**").permitAll()

                        // ----- USER -----
                        .requestMatchers("/enrollments/**").hasAnyRole("USER", "INSTRUCTOR", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/comments/**").hasAnyRole("USER", "INSTRUCTOR", "ADMIN")


                        //INSTRUCTOR
                        .requestMatchers(HttpMethod.POST, "/courses/**").hasAnyRole("INSTRUCTOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/courses/**").hasAnyRole("INSTRUCTOR", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/lesson/**").hasAnyRole("INSTRUCTOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/lesson/**").hasAnyRole("INSTRUCTOR", "ADMIN")

                        //  ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/courses/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/lesson/**").hasRole("ADMIN")

                        // Any other request: user must be logged in
                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
