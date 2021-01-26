package com.acm.app.mail.service;

import static com.acm.translations.EmailTranslation.NEW_USER_TEMPLATE_ONE;
import static com.acm.translations.EmailTranslation.NEW_USER_TEMPLATE_TWO;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.acm.app.mail.client.domain.MailMessage;
import com.acm.app.user.client.UserClient;
import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.library.globals.enums.WebRole;
import com.google.common.collect.Sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service class to send an email to a user
 *
 * @author Sam Butler
 * @since 08/04/2020
 */
@Service
public class MailService {

    @Autowired
    private UserClient userClient;

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

        MimeMessage builtMessage = buildMessage(sentMessage, session);
        transportMessage(builtMessage, session);

        sentMessage.setDateSent(new Date());
        return sentMessage;
    }

    /**
     * Service method that notify's all admins that a new user has been created.
     * 
     * @param user - The newly created user
     * @return {@link MailMessage} object to send.
     * @throws Exception
     */
    public MailMessage notifyAdminsNewUser(User user) throws Exception {
        MailMessage message = new MailMessage();
        UserGetRequest request = new UserGetRequest();

        request.setWebRole(Sets.newHashSet(WebRole.ADMIN));
        List<User> admins = userClient.getUsers(request);

        String newUserName = String.format("%s %s", user.getFirstName(), user.getLastName());

        message.setSubject("ACM Notification");
        message.setBody(
                String.format("%s <strong>%s</strong>. %s", NEW_USER_TEMPLATE_ONE, newUserName, NEW_USER_TEMPLATE_TWO));
        message.setRecipients(admins.stream().map(admin -> admin.getEmail()).collect(Collectors.toList()));

        return send(message);
    }

    /**
     * Builds out the properties needed to send a message
     *
     * @return {@link Properties} object to sent the fields needed to communicate
     *         with the gmail server
     */
    private Properties getProperties() {
        Properties props = new Properties();

        props.put("mail.from", "acm.web@outlook.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        return props;
    }

    /**
     * Builds out a MimeMessage object that will be created to send through the
     * gmail transport
     * 
     * @param message - message to build from.
     * @param session - the session object to add the message too.
     * @return {@link MimeMessage} object to be sent
     * @throws AddressException
     * @throws MessagingException
     */
    private MimeMessage buildMessage(MailMessage message, Session session) throws AddressException, MessagingException {
        MimeMessage mime = new MimeMessage(session);

        for (String recipient : message.getRecipients()) {
            mime.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        }

        mime.setSubject(message.getSubject());
        mime.setSentDate(new Date());
        mime.setContent(message.getBody(), "text/html");

        return mime;
    }

    /**
     * Creates a transport session and sends the message to the desired recipients
     * on the object.
     * 
     * @param msg     - message to be sent through the gmail server
     * @param session - session object to create the transport off of
     * @throws MessagingException
     */
    private void transportMessage(MimeMessage msg, Session session) throws MessagingException {
        Transport transport = session.getTransport("smtp");

        transport.connect(USERNAME, PASSWORD);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
}
