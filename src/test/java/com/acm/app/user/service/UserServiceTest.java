package com.acm.app.user.service;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.dao.UserDAO;
import com.acm.service.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

		List<User> userList = new ArrayList<>();
		userList.add(userResponse);

		when(userDao.getUser(Mockito.any(UserGetRequest.class))).thenReturn(userList);

		List<User> userTest = userService.getUser(request);

		assertEquals("Expect Users to be equal", userTest.get(0), userResponse);
	}
	
	@Test
	public void testGetUserCredentials() throws UserNotFoundException
	{
		User userResponse = new User();
		userResponse.setUsername("testuser");
		userResponse.setPassword("password");

		when(userDao.getUserCredentials(Mockito.any(UserGetRequest.class))).thenReturn(userResponse);

		User userTest = userService.getUserCredentials(new UserGetRequest());

		assertEquals("Users should be equal", userTest, userResponse);
	}
}
