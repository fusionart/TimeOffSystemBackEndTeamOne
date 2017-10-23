package com.tos.timeoffserver.security;



import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


import com.tos.timeoffserver.domain.entites.ApplicationUser;
import static com.tos.timeoffserver.security.SecurityConstants.EXPIRATION_TIME;
import static com.tos.timeoffserver.security.SecurityConstants.HEADER_STRING;
import static com.tos.timeoffserver.security.SecurityConstants.SECRET;
import static com.tos.timeoffserver.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	@CrossOrigin
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		System.out.println(" --------------JWTAuthenticationFilter----- 1 --------------------------------");
		System.out.println(" --------------" + req + " --------------------------------");
		try {
			System.out.println(" -------------- iner try --------------------------------");
			ApplicationUser creds = new ObjectMapper().readValue(req.getInputStream(), ApplicationUser.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	@CrossOrigin
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		System.out.println(" --------------JWTAuthenticationFilter----- 2 --------------------------------");
		String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		response.getWriter().write(TOKEN_PREFIX + token);
	}
}