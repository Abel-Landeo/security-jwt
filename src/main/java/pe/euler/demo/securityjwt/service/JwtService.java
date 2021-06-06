package pe.euler.demo.securityjwt.service;

public interface JwtService {

	String generateJwt(String user, String password);

	boolean isNotValid(String jwt);

}
