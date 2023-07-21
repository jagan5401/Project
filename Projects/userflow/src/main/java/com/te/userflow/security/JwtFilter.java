package com.te.userflow.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.te.userflow.exception.InvalidCredentialException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService detailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = null;
		String extractUsername = null;
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			jwt = header.substring(7);
			extractUsername = jwtUtil.extractUsername(jwt);
		} 

		//System.out.println("4");
		if (extractUsername != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = detailsService.loadUserByUsername(extractUsername);
			//System.out.println("5");
			if (jwtUtil.validateToken(jwt, userDetails)) {
			//	System.out.println("6");
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(null,
						userDetails, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);

	}

}
