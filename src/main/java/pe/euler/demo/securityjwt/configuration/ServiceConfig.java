package pe.euler.demo.securityjwt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pe.euler.demo.securityjwt.service.BookService;
import pe.euler.demo.securityjwt.service.JwtService;
import pe.euler.demo.securityjwt.service.impl.BookServiceImpl;
import pe.euler.demo.securityjwt.service.impl.JwtServiceImpl;

@Configuration
public class ServiceConfig {
	
	@Bean
	public JwtService getJwtService() {
		return new JwtServiceImpl();
	}
	
	@Bean
	public BookService getBookService() {
		return new BookServiceImpl();
	}

}
