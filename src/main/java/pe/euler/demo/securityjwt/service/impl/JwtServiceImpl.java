package pe.euler.demo.securityjwt.service.impl;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pe.euler.demo.securityjwt.service.JwtService;

public class JwtServiceImpl implements JwtService{
	
	private static final Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);

	private static final String SECRET_KEY = "our_s3cr3t";
	private static final SignatureAlgorithm SIGN_ALG = SignatureAlgorithm.HS256;
	private Key signingKey;
	
	public JwtServiceImpl() {
		byte[] secretBytes = SECRET_KEY.getBytes();
		signingKey = new SecretKeySpec(secretBytes, SIGN_ALG.getJcaName());
	}

	@Override
	public String generateJwt(String user, String password) {
		if (!mockLogin(user, password)) {
			throw new RuntimeException("User or password invalid");
		}
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		c.add(Calendar.SECOND, 20);
		Date exp = c.getTime();
		
		JwtBuilder builder = Jwts.builder()
				.setId(user)
				.setIssuedAt(now)
				.signWith(SIGN_ALG, signingKey)
				.setExpiration(exp);
		
		return builder.compact();
	}
	
	private boolean mockLogin(String user, String password) {
		if ("ledis".equals(user) && "ledis123".equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isNotValid(String jwt) {
		try {
			Jwts.parser().setSigningKey(signingKey).parse(jwt);
			return false;
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			return true;
		}
		
		
	}

}
