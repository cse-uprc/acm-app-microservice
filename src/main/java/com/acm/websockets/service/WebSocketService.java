package com.acm.websockets.service;

import com.acm.websockets.client.domain.Webnotification;
import static com.acm.websockets.client.domain.WebSocketGlobals.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate template;

    public <T> void sendWebNotification(Webnotification<T> message) throws Exception {
        message.setCreated(new Date());
        template.convertAndSend(NOTIFICATIONS, message);
    }
}
