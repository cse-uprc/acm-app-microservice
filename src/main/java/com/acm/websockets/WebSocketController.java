package com.acm.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.acm.websockets.client.domain.WebSocketGlobals.*;

/**
 * WebSocketController for notifications
 * 
 * @author Sam Butler
 * @since Dec 13, 2020
 */
@CrossOrigin
@RestController
@RequestMapping("/api/web-notification-app/notifications")
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping
    public void sendWebNotification(@RequestBody Object message) throws Exception {
        template.convertAndSend(NOTIFICATIONS, message);
    }
}
