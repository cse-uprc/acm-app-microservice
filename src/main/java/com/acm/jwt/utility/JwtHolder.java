package com.acm.jwt.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

/**
 * JwtHolder class to get common information from token
 * 
 * @author Sam Butler
 * @since 8/04/2020
 */
@Component("JwtHolder")
public class JwtHolder {

	private JwtParser jwtParser = Jwts.parser().setSigningKey("CA024AFFB510011EB554F04721BFB4279D11B06A");

	/**
	 * Get the current userId from the request headers token
	 * 
	 * @return int of the userId from the current token
	 */
	public int getRequiredUserId() {
		try {
			return Integer.parseInt(jwtParser.parseClaimsJws(getToken()).getBody().get("userId").toString());
		} catch (Exception e) {
			System.out.println("Invalid Token");
			return -1;
		}
	}

	/**
	 * Get the userId from the passed in token
	 * 
	 * @param token - String of the token to decode
	 * @return int of the userId from the current token
	 */
	public int getRequiredUserId(String token) {
		try {
			return Integer.parseInt(jwtParser.parseClaimsJws(token).getBody().get("userId").toString());
		} catch (Exception e) {
			System.out.println("Invalid Token");
			return -1;
		}
	}

	/**
	 * Get the current username from the request headers token
	 * 
	 * @return String of the username from the current token
	 */
	public String getRequiredUsername() {
		try {
			return jwtParser.parseClaimsJws(getToken()).getBody().get("username").toString();
		} catch (Exception e) {
			System.out.println("Invalid Token");
			return "";
		}
	}

	/**
	 * Get the username from the passed in token
	 * 
	 * @param token - String of the token to decode
	 * @return String of the username from the current token
	 */
	public String getRequiredUsername(String token) {
		try {
			return jwtParser.parseClaimsJws(token).getBody().get("username").toString();
		} catch (Exception e) {
			System.out.println("Invalid Token");
			return "";
		}
	}

	/**
	 * Get the current webRole from the request headers token
	 * 
	 * @return String of the webRole from the current token
	 */
	public String getWebRole() {
		try {
			return jwtParser.parseClaimsJws(getToken()).getBody().get("webRole").toString();
		} catch (Exception e) {
			System.out.println("Invalid Token");
			return "";
		}
	}

	/**
	 * Get the webRole from the passed in token
	 * 
	 * @param token - String of the token to decode
	 * @return String of the webRole from the current token
	 */
	public String getWebRole(String token) {
		try {
			return jwtParser.parseClaimsJws(token).getBody().get("webRole").toString();
		} catch (Exception e) {
			System.out.println("Invalid Token");
			return "";
		}
	}

	/**
	 * Get the current token passed in with the request
	 * 
	 * @return String of the token from the request headers
	 */
	public String getToken() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		return request.getHeader("Authorization").split(" ")[1];
	}
}
