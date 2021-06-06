package pe.euler.demo.securityjwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.euler.demo.securityjwt.model.Response;

@RestController
@RequestMapping("/hello")
public class HelloController {
	
	@RequestMapping(method = RequestMethod.GET)
	public Response get() {
		Response resp = new Response();
		resp.setStatus(true);
		resp.setMessage("from the Security Api");
		return resp;
	}

}
