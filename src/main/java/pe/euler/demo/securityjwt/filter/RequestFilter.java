package pe.euler.demo.securityjwt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import pe.euler.demo.securityjwt.service.JwtService;

@Component
@Order(1)
public class RequestFilter implements Filter{
	
	private final JwtService jwtService;
	
	public RequestFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	private final Logger log = LoggerFactory.getLogger(RequestFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		if (httpRequest.getRequestURI().startsWith("/jwt")) {
			chain.doFilter(request, response);
			return;
		}
		
		String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
		if (authHeader == null) {
			log.error("header Authorization not provided");
			((HttpServletResponse) response).sendError(401);
			return;
		}
		
		if (!authHeader.startsWith("Bearer ")) {
			log.error("Authorization header should start with Bearer ");
			((HttpServletResponse) response).sendError(500);
			return;
		}
		
		String jwt = authHeader.split(" ")[1];
		if (jwtService.isNotValid(jwt)) {
			log.error("Jwt not valid ");
			((HttpServletResponse) response).sendError(401);
			return;
		}
		
		chain.doFilter(request, response);		
		
	}

}
