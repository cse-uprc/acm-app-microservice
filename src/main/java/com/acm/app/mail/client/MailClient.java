package com.acm.app.mail.client;

import javax.mail.MessagingException;

import com.acm.app.mail.client.domain.MailMessage;
import com.acm.app.mail.rest.MailController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	 * @throws MessagingException - Thrown if sending a message fails
	 */
	public MailMessage sendMessage(MailMessage message) throws MessagingException {
		return mailController.sendMessage(message);
	}
}
