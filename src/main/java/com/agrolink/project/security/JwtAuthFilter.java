package com.agrolink.project.security;
//This Class is used by JWT to Automatically validation..

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.agrolink.project.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	
	private final CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)

			throws ServletException, IOException {

		try {
			String authHeader = request.getHeader("Authorization");

			String token = null;
			String email = null;

			if(authHeader != null && authHeader.startsWith("Bearer ")) {
				token = authHeader.substring(7);
				
				// Validate token FIRST before extracting claims
				if(jwtUtil.validateToken(token)) {
					email = jwtUtil.extractEmail(token);

					if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
						try {
							UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

							UsernamePasswordAuthenticationToken authToken =
									new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

							authToken.setDetails(
									new WebAuthenticationDetailsSource()
									.buildDetails(request)
							);

							SecurityContextHolder
							.getContext()
							.setAuthentication(authToken);
							
							log.debug("JWT Token validated successfully for user: {}", email);
						} catch (Exception e) {
							log.warn("Failed to load user details for email: {}", email, e);
						}
					}
				} else {
					log.warn("JWT Token validation failed");
				}
			}
		} catch (Exception e) {
			log.error("JWT filter error: {}", e.getMessage());
		}

		filterChain.doFilter(request, response);
	}

}
