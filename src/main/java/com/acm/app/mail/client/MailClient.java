package com.acm.app.mail.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acm.app.mail.client.domain.MailMessage;
import com.acm.app.mail.rest.MailController;
import com.acm.app.user.client.domain.User;

/**
 * Client for {@link MailController} to expose the given endpoint's to other
 * services.
 * 
 * @author Sam Butler
 * @since 8/04/2020
 */
@Component
public class MailClient {

	@Autowired
	private MailController mailController;

	/**
	 * Send's an email to a user based on the given {@link MailMessage} object
	 * 
	 * @param message - The message object to be sent
	 * @return {@link MailMessage} object of the send message
	 * @throws Exception
	 */
	public MailMessage sendMessage(MailMessage message) throws Exception {
		return mailController.sendMessage(message);
	}

	/**
	 * Client method that notify's admins when a new user is created so that the
	 * user can be approved or not.
	 *
	 * @param user - The new user that was created
	 * @return {@link MailMessage} object of the message that was sent
	 * @throws Exception
	 */
	public MailMessage notifyAdminsNewUser(User user) throws Exception {
		return mailController.notifyAdminsNewUser(user);
	}
}
