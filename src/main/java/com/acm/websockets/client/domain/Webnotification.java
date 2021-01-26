package com.acm.websockets.client.domain;

import java.util.Date;

/**
 * Webnotification object for websockets
 * 
 * @author Sam Butler
 * @since Dec 14, 2020
 */
public class Webnotification<T> {
    private Integer userId;
    private Date created;
    private T message;
    private WebnotificationType type;

    public Integer getId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public T getBody() {
        return message;
    }

    public void setBody(T message) {
        this.message = message;
    }

    public WebnotificationType getType() {
        return type;
    }

    public void setType(WebnotificationType type) {
        this.type = type;
    }
}
