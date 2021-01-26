package com.acm.jwt.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Date;

import com.acm.app.user.client.domain.User;
import com.acm.service.activeprofile.ActiveProfile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;

@SpringBootTest
@SuppressWarnings("unused")
public class JwtTokenUtilTest {

	@Spy
	@InjectMocks
	private JwtTokenUtil jwtTokenUtil;

	@Mock
	private ActiveProfile activeProfile;

	private final User user = new User();

	@BeforeEach
	public void setUpTests() {
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setUsername("TestUser123");
		user.setPassword("password");

		when(activeProfile.getWebUrl()).thenReturn("test");
		when(activeProfile.getMicroserviceUrl()).thenReturn("test");
	}

	@Test
	public void testCreateJwtToken() {
		String token = jwtTokenUtil.generateToken(user);
		assertNotNull("Valid Token", token);
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
	void testTokenDecode() {
		User testUser = new User();
		testUser.setUsername("test");
		testUser.setId(1);
		testUser.setPassword("password");
		String token = jwtTokenUtil.generateToken(testUser);

		Claims testToken = jwtTokenUtil.decodeToken(token);

		assertEquals(1, testToken.get("userId"));

	}

	@Test
	void testBrokenTokenDecode() throws MalformedJwtException {
		assertThrows(MalformedJwtException.class, () -> {
			String notAToken = "jfkldsjfklasd";
			Claims brokenToken = jwtTokenUtil.decodeToken(notAToken);
		});
	}
}
