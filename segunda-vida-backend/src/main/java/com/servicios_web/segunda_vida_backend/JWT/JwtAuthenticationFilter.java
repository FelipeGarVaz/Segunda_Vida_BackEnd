package com.servicios_web.segunda_vida_backend.JWT;
import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest(request);
        final String username;

        // Si no hay token, continúa con la cadena de filtros
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Obtener el nombre de usuario del token
        username = jwtService.getUsernameFromToken(token);

        // Si hay un nombre de usuario y no hay autenticación previa, intenta autenticar
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Verifica si el token es válido
            if (jwtService.isTokenValid(token, userDetails)) {
                // Crea el objeto de autenticación
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                // Establece los detalles de la autenticación
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establece el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                // Si el token es inválido, responde con un error 401
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o expirado");
                return;
            }
        }

        // Continúa con la cadena de filtros
        filterChain.doFilter(request, response);
    }

    //Método para obtener el token del encabezado de autorización
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}

