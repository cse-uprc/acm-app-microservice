package com.acm.app.mail.client.domain;

import java.util.Date;
import java.util.List;

/**
 * Object class to create a MailMessage object
 *
 * @author Sam Butler
 * @since 8/04/2020
 */
public class MailMessage {

    private String subject;
    private List<String> recipients;
    private String body;
    private Date dateSent;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipient) {
        this.recipients = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }
}
