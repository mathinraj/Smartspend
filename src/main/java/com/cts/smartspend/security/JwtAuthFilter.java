package com.cts.smartspend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.cts.smartspend.security.CustomUserDetailsService;

import java.io.IOException;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    // Filter to validate the JWT token and set authentication in security context
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get the JWT token from the request
        String token = getTokenFromRequest(request);
        // If a token is present, validate it
        if (token != null){
            // Get the username from the JWT token
            String username = jwtUtils.getUsernameFromToken(token);
            // Load the user details using the username
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            // If the username is valid and the token is valid, set the authentication context
            if (StringUtils.hasText(username) && jwtUtils.isTokenValid(token, userDetails)){

                // Create an authentication token with the user details
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                // Set the details of the authentication token
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Set the authentication object in the security context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
    // Helper method to extract the token from the Authorization header
    private String getTokenFromRequest(HttpServletRequest request){
        // Get the Authorization header from the request
        String token = request.getHeader("Authorization");
        // If the header contains a token starting with "Bearer ", extract the token
        if (StringUtils.hasText(token) && StringUtils.startsWithIgnoreCase(token, "Bearer ")){
            return token.substring(7); // Return the token without the "Bearer " prefix
        }
        return null; // Return null if no valid token is found
    }
}