package edu.itmo.isbd.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.itmo.isbd.dto.UserDto;
import edu.itmo.isbd.model.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationFilter extends GenericFilter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		byte[] inputStreamBytes = StreamUtils.copyToByteArray(request.getInputStream());
		UserDto user = new ObjectMapper().readValue(inputStreamBytes, UserDto.class);
		if (!ObjectUtils.allNotNull(user.getLogin(), user.getPassword(), user.getPhone(), user.getMail(), user.getRole()))
			((HttpServletResponse)response).sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Incomplete user data was given.");
		else chain.doFilter(request, response);
	}
}
