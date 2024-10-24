package com.servicios_web.segunda_vida_backend.Configuration;

import com.servicios_web.segunda_vida_backend.JWT.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(authRequest -> authRequest
//                                .requestMatchers("/auth/**","login", "doc/**", "/swagger-ui/**",
//                                        "/v3/api-docs/**","/static/**","shoppings/**","users/**","category/**",
//                                        "product/**","reviews/**","sales/**").permitAll()
//                                .anyRequest().authenticated()
                                        .anyRequest().permitAll()
                )
                .sessionManagement(sessionManager-> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
