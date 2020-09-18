package eu.epitech.businesscard.configuration;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstant {
	
	@Value("${jwt.secret}")
	public static String JWT_SECRET;
	public static final long EXPIRATION_TIME = 432_000_000; // 5 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_TYPE = "Authorization";
	public static final String CLIENT_DOMAIN_URL = "*";

}
