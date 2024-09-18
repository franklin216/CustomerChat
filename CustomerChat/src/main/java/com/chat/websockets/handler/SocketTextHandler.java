package com.chat.websockets.handler;

import com.chat.websockets.manager.WebSocketSessionManager;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.LinkedList;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

    private WebSocketSessionManager webSocketSessionManager;

    public SocketTextHandler(WebSocketSessionManager webSocketSessionManager) {
        this.webSocketSessionManager = webSocketSessionManager;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.webSocketSessionManager.addWebSocketSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        this.webSocketSessionManager.removeWebSocketSession(session);
    }

    public LinkedList<Pair> unanswerdQuestions = new LinkedList<Pair>();
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws IOException {
        String payload = message.getPayload();

        System.out.println("sender id: "+session.getId());
        Pair<WebSocketSession , TextMessage > pair = new Pair<WebSocketSession , TextMessage >( session,  message);

        unanswerdQuestions.add(pair);
//        this.webSocketSessionManager.getWebSocketSessionsExcept(session).forEach(webSocketSession -> {
//
//            System.out.println("senden an:"+webSocketSession.getId());
//            try {
//                webSocketSession.sendMessage(new TextMessage("message = "+payload));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }


//    public void sendMessageToUser(WebSocketSession session, String answer) throws IOException {
//        session.sendMessage(new TextMessage("message = "+answer));
//    }


}