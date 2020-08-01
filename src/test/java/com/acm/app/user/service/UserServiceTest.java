package com.acm.app.user.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.acm.app.user.client.domain.request.UserGetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.dao.UserDAO;

@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@InjectMocks
	private UserGetRequest request;

	@Mock
	private UserDAO userDao;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUsers() {
		User userResponse = new User();
		userResponse.setFirstName("Test");
		userResponse.setLastName("Last");

		when(userDao.getUser(Mockito.any(UserGetRequest.class))).thenReturn(userResponse);

		User userTest = userService.getUser(request);

		assertEquals("Expect Users to be equal", userTest, userResponse);
	}
}
