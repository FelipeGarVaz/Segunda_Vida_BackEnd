package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.JWT.JwtService;
import com.servicios_web.segunda_vida_backend.Model.AuthResponse;
import com.servicios_web.segunda_vida_backend.Model.LoginRequest;
import com.servicios_web.segunda_vida_backend.Model.RegisterRequest;
import com.servicios_web.segunda_vida_backend.Model.User;
import com.servicios_web.segunda_vida_backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUserName(request.getUsername()).orElseThrow();
        String token= jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .userName(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();

        userRepository.save(user);

        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }
}