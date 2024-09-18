package com.chat.websockets.utils;

import com.chat.websockets.handler.SocketTextHandler;
import org.javatuples.Pair;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class AnswerMessages implements Runnable {
    private final SocketTextHandler socketTextHandler;

    public AnswerMessages(SocketTextHandler socketTextHandler) {
        this.socketTextHandler = socketTextHandler;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            if (!socketTextHandler.unanswerdQuestions.isEmpty()) {
                Pair<WebSocketSession, TextMessage> pair = socketTextHandler.unanswerdQuestions.pop();
                try {
                    pair.getValue0().sendMessage(new TextMessage("This is the answer to question: " + pair.getValue1().getPayload()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}