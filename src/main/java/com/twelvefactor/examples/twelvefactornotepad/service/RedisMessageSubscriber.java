package com.twelvefactor.examples.twelvefactornotepad.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twelvefactor.examples.twelvefactornotepad.dto.WebSocketMessage;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class RedisMessageSubscriber implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RedisMessageSubscriber.class);
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    public RedisMessageSubscriber(SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Callback for processing event messages published on Redis channels.
     * This method is invoked by the MessageListenerAdapter configured in RedisConfig.
     * @param message The actual message body as well as the channel it was sent to.
     * @param pattern The pattern that matched the channel (if specified, otherwise null).
     */
    @Override
    public void onMessage(@NonNull Message message, byte[] pattern) {
        try {
            WebSocketMessage wsMessage = objectMapper.readValue(message.getBody(), WebSocketMessage.class);
            logger.info(
                    "Received message from Redis channel '{}': Action: {}, Payload Type: {}",
                    new String(message.getChannel()),
                    wsMessage.action(), // Using record accessor
                    wsMessage.payload() != null ? wsMessage.payload().getClass().getSimpleName() : "null");

            // Forward to local STOMP clients connected to this application instance
            messagingTemplate.convertAndSend("/topic/notes", wsMessage);
            logger.debug("Forwarded message to /topic/notes: {}", wsMessage);

        } catch (Exception e) {
            logger.error(
                    "Error processing message from Redis: Body: {}, Channel: {}",
                    new String(message.getBody()),
                    new String(message.getChannel()),
                    e);
        }
    }
}
