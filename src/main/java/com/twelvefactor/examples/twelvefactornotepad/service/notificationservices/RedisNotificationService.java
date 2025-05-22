package com.twelvefactor.examples.twelvefactornotepad.service.notificationservices;

import com.twelvefactor.examples.twelvefactornotepad.config.RedisConfig;
import com.twelvefactor.examples.twelvefactornotepad.domain.Note;
import com.twelvefactor.examples.twelvefactornotepad.dto.WebSocketMessage;
import com.twelvefactor.examples.twelvefactornotepad.service.NotificationService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class RedisNotificationService implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(RedisNotificationService.class);
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisNotificationService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
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
            redisTemplate.convertAndSend(RedisConfig.REDIS_CHANNEL_NOTEPAD_UPDATES, message);
            logger.info(
                    "Published {} event to Redis channel {}: {}",
                    action,
                    RedisConfig.REDIS_CHANNEL_NOTEPAD_UPDATES,
                    message);
        } catch (Exception e) {
            logger.error(
                    "Error publishing {} event to Redis for note id {}: {}",
                    action,
                    (note != null ? note.getId() : "null"),
                    e.getMessage(),
                    e);
        }
    }
}
