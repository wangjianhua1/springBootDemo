package com.example.demo.websocket;

import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@EnableWebSocket
@ServerEndpoint("/websocket")
public class WebSocketTest {
    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        System.out.println("Received:" + message);
        session.getBasicRemote().sendText("this is the first server message");//send first message to the client

        int sentMessages = 0;
        while (sentMessages < 3) {
            Thread.sleep(5000);
            session.getBasicRemote().sendText("send count:" + sentMessages);
            sentMessages++;
        }
        session.getBasicRemote().sendText("end");
    }

    @OnOpen
    public void onOpen(){
        System.out.println("client connected");
    }

    @OnClose
    public void onClose(){
        System.out.println("connection closed");
    }
}
