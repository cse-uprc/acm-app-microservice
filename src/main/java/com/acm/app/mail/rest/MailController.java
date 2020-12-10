package com.acm.app.mail.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acm.app.mail.client.domain.MailMessage;
import com.acm.app.mail.service.MailService;
import com.acm.app.user.client.domain.User;

/**
 * Controller to send email's to users
 *
 * @author Sam Butler
 * @since 8/04/2020
 */
@CrossOrigin
@RestController
@RequestMapping("api/acm/mail")
public class MailController {

	@Autowired
	private MailService mailService;

	/**
	 * Sends an email to a user based on the given {@link MailMessage} object
	 *
	 * @param message - The message object to be sent
	 * @return {@link MailMessage} object of the send message
	 * @throws Exception
	 */
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public MailMessage sendMessage(@RequestBody MailMessage message) throws Exception {
		return mailService.send(message);
	}

	/**
	 * Notify's admins when a new user is created so that the user can be approved
	 * or not.
	 *
	 * @param user - The new user that was created
	 * @return {@link MailMessage} object of the message that was sent
	 * @throws Exception
	 */
	@PostMapping(value = "/notify-admins", produces = APPLICATION_JSON_VALUE)
	public MailMessage notifyAdminsNewUser(User user) throws Exception {
		return mailService.notifyAdminsNewUser(user);
	}
}
