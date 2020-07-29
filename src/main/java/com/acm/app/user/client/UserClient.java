package com.acm.app.user.client;

		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.stereotype.Component;

		import com.acm.app.user.client.domain.User;
		import com.acm.app.user.rest.UserController;

@Component
public class UserClient {

	@Autowired
	private UserController userController;

	public User getUser(int id) {
		return userController.getUser(id);
	}
}
