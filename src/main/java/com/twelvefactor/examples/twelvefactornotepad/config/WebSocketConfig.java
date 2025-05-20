package com.twelvefactor.examples.twelvefactornotepad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Messages whose destination starts with "/topic" will be routed to the message broker.
        registry.enableSimpleBroker("/topic");
        // Messages whose destination starts with "/app" are routed to message-handling methods (e.g. @MessageMapping)
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register the "/ws-notepad" endpoint, enabling SockJS fallback options
        registry.addEndpoint("/ws-notepad").withSockJS();
    }
}
