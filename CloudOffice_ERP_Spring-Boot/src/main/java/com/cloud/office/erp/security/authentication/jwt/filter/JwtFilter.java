package com.cloud.office.erp.security.authentication.jwt.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cloud.office.erp.security.authentication.jwt.util.JwtUtil;
import com.cloud.office.erp.security.authentication.service.CustomUserDetailsService;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {

	String token = null;
	String userName = null;
	String authorizationHeader = httpServletRequest.getHeader("Authorization");
	
	if(authorizationHeader != null && authorizationHeader != "") {
		 authorizationHeader = authorizationHeader.replace("\"", "");
	}
	
	try {
		
	if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
		token = authorizationHeader.substring(7);
		userName = jwtUtil.extractUsername(token);
	}
	
	if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	  UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
	  
	  if (jwtUtil.validateToken(token, userDetails)) {

          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          usernamePasswordAuthenticationToken
                  .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
	  
	}
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
