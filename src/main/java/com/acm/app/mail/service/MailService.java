package com.acm.app.mail.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

import com.acm.app.mail.client.domain.MailMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Service class to send an email to a user
 *
 * @author Sam Butler
 * @since 08/04/2020
 */
@Component
public class MailService {

    @Value("${spring.mail.username}")
    private String USERNAME;

    @Value("${spring.mail.password}")
    private String PASSWORD;

    /**
     * Send a message to a user given the {@link MailMessage} object
     *
     * @param sentMessage - {@link MailMessage} object to send
     * @return {@link MailMessage} of what was sent and when it was sent
     * @throws Exception
     */
    public MailMessage send(MailMessage sentMessage) throws Exception {
        Session session = Session.getInstance(getProperties(), null);
        MimeMessage message = new MimeMessage(session);

        for (String recipient : sentMessage.getRecipients()) {
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        }

        message.setSubject(sentMessage.getSubject());
        message.setSentDate(new Date());
        message.setText(sentMessage.getBody());

        Transport transport = session.getTransport("smtp");

        transport.connect(USERNAME, PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

        sentMessage.setDateSent(new Date());
        return sentMessage;
    }

    /**
     * Builds out the properties needed to send a message
     *
     * @return {@link Properties} object to sent the fields needed to communicate
     *         with the gmail server
     */
    private Properties getProperties() {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.from", "sambutler1017@gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.debug", "true");

        return props;
    }
}
