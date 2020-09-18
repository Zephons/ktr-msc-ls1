package eu.epitech.businesscard.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtAuthorization extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", SecurityConstant.CLIENT_DOMAIN_URL);

		response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, "
				+ "Content-Type, Access-Control-Request-Method, " + "Access-Control-Request-Headers, Authorization");

		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin, " + "Access-Control-Allow-Creentials, " + "Authorization");

		response.addHeader("Access-Control-Allow-Methods", "GET," + "POST, " + "DELETE");

		if ((request.getMethod().equalsIgnoreCase("OPTIONS"))) {
			try {
				response.setStatus(HttpServletResponse.SC_OK);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String jwtToken = request.getHeader(SecurityConstant.HEADER_TYPE);
			if (jwtToken == null || !jwtToken.startsWith(SecurityConstant.TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}
			JWT.require(Algorithm.HMAC256(SecurityConstant.JWT_SECRET));
			DecodedJWT jwt = JWT.decode(jwtToken.substring(SecurityConstant.TOKEN_PREFIX.length()));
			String username = jwt.getSubject();
			UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(username,
					null);
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			filterChain.doFilter(request, response);
		}
	}
}
