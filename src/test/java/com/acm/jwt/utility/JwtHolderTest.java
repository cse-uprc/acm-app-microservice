package com.acm.jwt.utility;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.acm.app.user.client.domain.User;
import com.acm.jwt.config.JwtTokenUtil;
import com.acm.service.activeprofile.ActiveProfile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtHolderTest {
	@Spy
	private JwtHolder jwtHolder;

	@InjectMocks
	private JwtTokenUtil jwtUtil;

	@Mock
	private ActiveProfile activeProfile;

	private User user;

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setFirstName("TestFirst");
		user.setLastName("TestLast");
		user.setId(1);
		user.setUsername("TestUser");
		user.setPassword("password");

		when(activeProfile.getWebUrl()).thenReturn("test");
		when(activeProfile.getMicroserviceUrl()).thenReturn("test");
	}

	@Test
	public void testGetRequiredUserIdNoParams() {
		String token = jwtUtil.generateToken(user);

		doReturn(token).when(jwtHolder).getToken();

		int userId = jwtHolder.getRequiredUserId();

		assertEquals("User Id should be 1", 1, userId);
	}

	@Test
	public void testGetRequiredUserIdWithParams() {
		String tokenValid = jwtUtil.generateToken(user);
		String tokenInvalid = "alkfhavniqon1234221inf";

		int userIdValid = jwtHolder.getRequiredUserId(tokenValid);
		int userIdInvalid = jwtHolder.getRequiredUserId(tokenInvalid);

		assertEquals("User Id should be TestUser", 1, userIdValid);
		assertEquals("User Id should be -1", -1, userIdInvalid);
	}

	@Test
	public void testGetRequiredUsernameNoParams() {
		String token = jwtUtil.generateToken(user);

		doReturn(token).when(jwtHolder).getToken();

		String username = jwtHolder.getRequiredUsername();

		assertEquals("Username should be TestUser", "TestUser", username);
	}

	@Test
	public void testGetRequiredUsernameWithParams() {
		String tokenValid = jwtUtil.generateToken(user);
		String tokenInvalid = "alkfhavniqon1234221inf";

		String usernameValid = jwtHolder.getRequiredUsername(tokenValid);
		String usernameInvalid = jwtHolder.getRequiredUsername(tokenInvalid);

		assertEquals("Username should be TestUser", "TestUser", usernameValid);
		assertEquals("Username should be empty String", "", usernameInvalid);
	}
}
