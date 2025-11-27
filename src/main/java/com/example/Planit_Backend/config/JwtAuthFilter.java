package com.example.Planit_Backend.config;


import com.example.Planit_Backend.repository.UserRepository;
import com.example.Planit_Backend.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository ;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {

        String header = req.getHeader("Authorization");

        if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if(jwtUtils.validateToken(token)) {

                String email = jwtUtils.getEmailFromToken(token);

                var userOpt = userRepository.findByEmail(email);

                if( userOpt.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null){

                    var user = userOpt.get();

                    var authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));

                    var auth = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            authorities
                    );

                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        chain.doFilter(req, res);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req){
        String path = req.getServletPath();

        return path.startsWith("/api/auth/") || path.startsWith("/public/");
    }
}
