package com.acm.jwt.utility;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import com.acm.app.user.client.domain.User;
import com.acm.jwt.config.JwtTokenUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtHolderTest {

	@Spy
	private JwtHolder jwtHolder;

	private User user;

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setFirstName("TestFirst");
		user.setLastName("TestLast");
		user.setUserId(1);
		user.setUsername("TestUser");
		user.setPassword("password");
	}

	@Test
	public void testGetRequiredUserIdNoParams() {
		JwtTokenUtil tokenGenerator = new JwtTokenUtil();
		String token = tokenGenerator.generateToken(user);

		doReturn(token).when(jwtHolder).getToken();

		int userId = jwtHolder.getRequiredUserId();

		assertEquals("User Id should be 1", 1, userId);
	}

	@Test
	public void testGetRequiredUserIdWithParams() {
		JwtTokenUtil tokenGenerator = new JwtTokenUtil();

		String tokenValid = tokenGenerator.generateToken(user);
		String tokenInvalid = "alkfhavniqon1234221inf";

		int userIdValid = jwtHolder.getRequiredUserId(tokenValid);
		int userIdInvalid = jwtHolder.getRequiredUserId(tokenInvalid);

		assertEquals("User Id should be TestUser", 1, userIdValid);
		assertEquals("User Id should be -1", -1, userIdInvalid);
	}

	@Test
	public void testGetRequiredUsernameNoParams() {
		JwtTokenUtil tokenGenerator = new JwtTokenUtil();
		String token = tokenGenerator.generateToken(user);

		doReturn(token).when(jwtHolder).getToken();

		String username = jwtHolder.getRequiredUsername();

		assertEquals("Username should be TestUser", "TestUser", username);
	}

	@Test
	public void testGetRequiredUsernameWithParams() {
		JwtTokenUtil tokenGenerator = new JwtTokenUtil();

		String tokenValid = tokenGenerator.generateToken(user);
		String tokenInvalid = "alkfhavniqon1234221inf";

		String usernameValid = jwtHolder.getRequiredUsername(tokenValid);
		String usernameInvalid = jwtHolder.getRequiredUsername(tokenInvalid);

		assertEquals("Username should be TestUser", "TestUser", usernameValid);
		assertEquals("Username should be empty String", "", usernameInvalid);
	}
}
