package com.twelvefactor.examples.twelvefactornotepad.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twelvefactor.examples.twelvefactornotepad.dto.WebSocketMessage;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class RedisMessageSubscriber implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RedisMessageSubscriber.class);
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RedisMessageSubscriber(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void onMessage(@NonNull Message message, byte[] pattern) {
        try {
            WebSocketMessage webSocketMessage = objectMapper.readValue(message.getBody(), WebSocketMessage.class);
            logger.info(
                    "Received message from Redis channel {}: {}", new String(message.getChannel()), webSocketMessage);
            simpMessagingTemplate.convertAndSend("/topic/notes", webSocketMessage);
            logger.info("Forwarded message to STOMP topic /topic/notes: {}", webSocketMessage);
        } catch (Exception e) {
            logger.error("Error processing message from Redis: {}", e.getMessage(), e);
        }
    }
}
