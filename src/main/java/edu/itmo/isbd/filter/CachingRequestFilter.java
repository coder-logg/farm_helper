package edu.itmo.isbd.filter;

import edu.itmo.isbd.servlet.CachedBodyHttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(1)
@Component
public class CachingRequestFilter extends GenericFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		CachedBodyHttpServletRequest cachedRequest = new CachedBodyHttpServletRequest((HttpServletRequest) request);
		chain.doFilter(cachedRequest, response);
	}
}
