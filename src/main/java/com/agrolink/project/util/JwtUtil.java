package com.agrolink.project.util;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	public String generateToken(String email)
	{
		Key key = Keys.hmacShaKeyFor(secret.getBytes());
		
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	private Claims extractAllClaims(String token) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
					.build()
					.parseClaimsJws(token)
					.getBody();
		} catch (ExpiredJwtException e) {
			log.warn("JWT token has expired: {}", e.getMessage());
			throw e;
		} catch (MalformedJwtException e) {
			log.warn("Invalid JWT format: {}", e.getMessage());
			throw e;
		} catch (SignatureException e) {
			log.warn("JWT signature validation failed: {}", e.getMessage());
			throw e;
		} catch (UnsupportedJwtException e) {
			log.warn("JWT token is not supported: {}", e.getMessage());
			throw e;
		} catch (JwtException e) {
			log.warn("JWT processing error: {}", e.getMessage());
			throw e;
		} catch (IllegalArgumentException e) {
			log.warn("JWT claims string is empty: {}", e.getMessage());
			throw e;
		}
	}
	
	public String extractEmail(String token)
	{
		return extractAllClaims(token).getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			extractAllClaims(token);
			return true;
		} catch (Exception e) {
			log.error("Token validation failed: {}", e.getMessage());
			return false;
		}
	}


}
