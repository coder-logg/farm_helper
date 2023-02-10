package edu.itmo.isbd.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public AuthenticationFilter() {
		super();
		setAuthenticationSuccessHandler((request, response, authentication) ->
		{
			SecurityContextHolder.getContext().setAuthentication(authentication);
			request.getRequestDispatcher(request.getServletPath() + request.getPathInfo()).forward(request, response);
		});
		setAuthenticationFailureHandler((request, response, authenticationException) -> {
			response.getOutputStream().print(authenticationException.getMessage());
		});
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//		request.l
		return super.attemptAuthentication(request, response);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		super.doFilter(request, response, chain);
	}
}
