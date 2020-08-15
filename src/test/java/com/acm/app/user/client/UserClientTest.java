package com.acm.app.user.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.rest.UserController;
import com.acm.library.globals.exceptions.UserNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserClientTest {
	@InjectMocks
	private UserClient userClient;

	@Mock
	private UserController userController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUsers() throws UserNotFoundException {
		User userResponse = new User();
		userResponse.setFirstName("Test");
		userResponse.setLastName("Last");

		List<User> userList = new ArrayList<>();
		userList.add(userResponse);

		when(userController.getUsers(Mockito.any(UserGetRequest.class))).thenReturn(userList);

		List<User> userTest = userClient.getUsers(new UserGetRequest());

		assertEquals("Users should be equal", userTest.get(0), userResponse);
	}
}