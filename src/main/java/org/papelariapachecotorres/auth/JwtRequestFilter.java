package org.papelariapachecotorres.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        logger.info("=== JWT Filter Processing ===");
        logger.info("Method: {} | URI: {}", request.getMethod(), request.getRequestURI());
        logger.info("Authorization header: {}", authorizationHeader != null ? "Present" : "Missing");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            logger.info("JWT Token extracted: {}...", jwt.substring(0, Math.min(20, jwt.length())));
            username = JwtUtil.validateToken(jwt);
            logger.info("JWT validation result - username: {}", username != null ? username : "FAILED");
        } else {
            logger.warn("No Bearer token found in Authorization header");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Como não temos um UserDetailsService real, criamos um UserDetails mock
            UserDetails userDetails = new User(username, "", new ArrayList<>());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.info("✅ Authentication set successfully for user: {}", username);
        } else {
            if (username == null) {
                logger.error("❌ Authentication FAILED - username is null");
            } else {
                logger.info("Authentication already set");
            }
        }
        chain.doFilter(request, response);
    }
} 