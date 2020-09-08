package com.bess.springboot.wfx.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/8 11:03
 */
@Component
@ServerEndpoint("/webSocket/{orderId}")
public class WebSocket {
    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();
    @OnOpen
    public void open(@PathParam("orderId") String orderId, Session session){
        System.out.println("----------open");
        sessionMap.put(orderId,session);
    }
    @OnClose
    public void close(@PathParam("orderId")String oid ){
        System.out.println("------"+oid);
        sessionMap.remove(oid);
    }
    public static void sendMessage(String oid,String msg) throws IOException {
        Session session = sessionMap.get(oid);
        session.getBasicRemote().sendText(msg);
    }
}
