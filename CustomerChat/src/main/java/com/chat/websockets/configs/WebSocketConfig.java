package com.chat.websockets.configs;

import com.chat.websockets.utils.AnswerMessages;
import com.chat.websockets.handler.SocketTextHandler;
import com.chat.websockets.manager.WebSocketSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    WebSocketSessionManager webSocketSessionManager;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        SocketTextHandler socketTextHandler = new SocketTextHandler(this.webSocketSessionManager);
        registry.addHandler(socketTextHandler, "/user").
                setAllowedOriginPatterns("*");

        new Thread(new AnswerMessages(socketTextHandler)).start();

    }
}