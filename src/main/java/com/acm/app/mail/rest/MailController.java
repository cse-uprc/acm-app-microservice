package com.acm.app.mail.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.mail.MessagingException;

import com.acm.app.mail.client.domain.MailMessage;
import com.acm.app.mail.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to send email's to users
 *
 * @author Sam Butler
 * @since 8/04/2020
 */
@CrossOrigin
@RestController
@RequestMapping("api/acm/mail")
@Controller
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * Sends an email to a user based on the given {@link MailMessage} object
     *
     * @param message - The message object to be sent
     * @return {@link MailMessage} object of the send message
     * @throws MessagingException if there is a problem with the email
     */
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public MailMessage sendMessage(@RequestBody MailMessage message) throws MessagingException {
        return mailService.send(message);
    }
}
