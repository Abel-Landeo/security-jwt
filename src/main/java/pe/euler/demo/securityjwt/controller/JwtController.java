package pe.euler.demo.securityjwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.euler.demo.securityjwt.model.Login;
import pe.euler.demo.securityjwt.model.Response;
import pe.euler.demo.securityjwt.service.JwtService;

@RestController
@RequestMapping("/jwt")
public class JwtController {
	
	Logger log = LoggerFactory.getLogger(JwtController.class);
	
	private final JwtService jwtService;
	
	public JwtController(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Response logIn(@RequestBody Login login) {
		Response resp = new Response();
		try {
			String token = jwtService.generateJwt(login.getUser(), login.getPassword());
			resp.setStatus(true);
			resp.setMessage(token);
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			resp.setStatus(false);
			resp.setMessage(e.getMessage());
			
		}
		return resp;
	}

}
