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

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	
	
	private final CustomUserDetailsService custommUserDetailsService;
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)

			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

		String token = null;
		String email = null ;
		;



		if(authHeader != null &&  authHeader.startsWith("Bearer "))
		{
			token = authHeader.substring(7);
			email=jwtUtil.extractEmail(token);


			if(email != null && SecurityContextHolder.getContext()
					.getAuthentication()==null)
			{
				UserDetails userDetails = custommUserDetailsService.loadUserByUsername(email);

				if(jwtUtil.validateToken(token))
				{
					UsernamePasswordAuthenticationToken authToken =
							new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities()
									);

					 authToken.setDetails(
		                        new WebAuthenticationDetailsSource()
		                                .buildDetails(request)
		                );

					 SecurityContextHolder
                     .getContext()
                     .setAuthentication(authToken);
					 
				}
			}
		}

		filterChain.doFilter(request, response);
	}
	
	
	
	

}
