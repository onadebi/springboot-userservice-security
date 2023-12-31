package dev.onaxsys.onaxsecurity.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.onaxsys.onaxsecurity.user.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor // Used to Create constructor with all fields declared as final fields in this class and initialize them with the given arguments.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService _JwtService;
    private final IUserService _userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request
    , @NonNull HttpServletResponse response
    , @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        jwt = authHeader.substring(7);
        userEmail = _JwtService.extractUsername(jwt);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        }
    }
    
}
