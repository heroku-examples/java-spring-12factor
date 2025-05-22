package com.twelvefactor.examples.twelvefactornotepad.service.notificationservices;

import com.twelvefactor.examples.twelvefactornotepad.domain.Note;
import com.twelvefactor.examples.twelvefactornotepad.dto.WebSocketMessage;
import com.twelvefactor.examples.twelvefactornotepad.service.NotificationService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Profile("!prod")
public class StompNotificationService implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(StompNotificationService.class);
    private final SimpMessagingTemplate simpMessagingTemplate;

    public StompNotificationService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void publishEvent(String action, Note note) {
        String hostname = "Unknown";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.warn("Could not determine hostname for WebSocketMessage: {}", e.getMessage());
        }
        WebSocketMessage message = new WebSocketMessage(action, note, hostname);
        try {
            simpMessagingTemplate.convertAndSend("/topic/notes", message);
            logger.info("Published {} event to STOMP topic /topic/notes: {}", action, message);
        } catch (Exception e) {
            logger.error(
                    "Error publishing {} event to STOMP for note id {}: {}",
                    action,
                    (note != null ? note.getId() : "null"),
                    e.getMessage(),
                    e);
        }
    }
}
