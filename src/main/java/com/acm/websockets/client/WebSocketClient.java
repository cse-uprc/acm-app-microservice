package com.acm.websockets.client;

import com.acm.websockets.client.domain.Webnotification;
import com.acm.websockets.service.WebSocketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Client for {@link WebSocketService} to expose the given endpoint's to other
 * services.
 * 
 * @author Sam Butler
 * @since Dec 14, 2020
 */
@Component
public class WebSocketClient {
    @Autowired
    private WebSocketService service;

    public <T> void sendWebNotification(Webnotification<T> message) throws Exception {
        service.sendWebNotification(message);
    }
}
