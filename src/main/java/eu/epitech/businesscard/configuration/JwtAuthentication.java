package eu.epitech.businesscard.configuration;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.epitech.businesscard.model.Profile;

public class JwtAuthentication extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JwtAuthentication(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		Profile profile;
		
		try {
			profile = objectMapper.readValue(request.getInputStream(), Profile.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to convert Json into Java Object: " + e);
		}
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				profile.getUsername(),
				profile.getPassword()));
		}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		
		User user = (User) authentication.getPrincipal();
	
		String jwtToken = JWT.create()
				.withIssuer("Epitech Business")
				.withSubject(user.getUsername())
				.withExpiresAt(new Date (System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
				.sign(Algorithm.HMAC256(SecurityConstant.JWT_SECRET));
		
		response.addHeader(SecurityConstant.HEADER_TYPE, SecurityConstant.TOKEN_PREFIX+jwtToken);
	}
}

