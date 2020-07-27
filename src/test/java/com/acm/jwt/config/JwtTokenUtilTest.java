package com.acm.jwt.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.acm.app.user.client.domain.UserCredentials;

@SpringBootTest
public class JwtTokenUtilTest {

	@Spy
	private JwtTokenUtil jwtTokenUtil;

	private UserCredentials user = new UserCredentials();

	@BeforeEach
	public void setUpTests() {
		MockitoAnnotations.initMocks(this);
		user.setId(1);
		user.setName("Test User");
		user.setUsername("TestUser123");
		user.setPassword("password");
	}

	@Test
	public void testCreateJwtToken() {
		String token = jwtTokenUtil.generateToken(user);
		assertNotNull("Valid Token", token);
	}

	@Test
	public void testGetUsernameFromToken() {
		String token = jwtTokenUtil.generateToken(user);
		String username = jwtTokenUtil.getUsernameFromToken(token);

		assertEquals("Valid Token", username, user.getUsername());
	}

	@Test
	public void testGetExpirationDateFromToken() {
		String token = jwtTokenUtil.generateToken(user);
		Date date = jwtTokenUtil.getExpirationDateFromToken(token);

		assertNotNull("Date is not null", date);
		assertTrue("Expiration is greater than current date", date.after(new Date()));
	}

	@Test
	public void testIsTokenExpired() {
		String token = jwtTokenUtil.generateToken(user);

		assertFalse("Token should NOT be expired", jwtTokenUtil.isTokenExpired(token));

		doReturn(new Date(System.currentTimeMillis() - 100000)).when(jwtTokenUtil)
				.getExpirationDateFromToken(anyString());

		assertTrue("Token should be expired", jwtTokenUtil.isTokenExpired(token));
	}

	@Test
	public void testIsValidToken() {
		String tokenValid = jwtTokenUtil.generateToken(user);
		String tokenInvalid = "flkasdjfisdngoiawng1232124nslingsidga.eqwkrnqoingfewi";

		assertFalse("Token is Invalid", jwtTokenUtil.isValidToken(tokenInvalid));
		assertTrue("Token is Valid", jwtTokenUtil.isValidToken(tokenValid));
	}
}