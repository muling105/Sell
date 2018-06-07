package com.ztes.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websoket服务端
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@Component
@ServerEndpoint("/websocket")
@Slf4j
public class WebSocket {

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    //连接建立成功调用的方法
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSockets.add(this);
        log.info("【有新的websocket连接】, 总数 = {}", webSockets.size());
    }

    //连接关闭调用的方法
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        log.info("【websocket断开连接】, 总数 = {}", webSockets.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param msg 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String msg){
        log.info("【收到客户端发来消息】 msg = {}", msg);
        sendMessage(msg);
    }

    //发送消息
    public void sendMessage(String msg){
        try {
            for(WebSocket webSocket : webSockets){
                log.info("【广播消息】 msg = {}", msg);
                webSocket.session.getBasicRemote().sendText(msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
     @OnError
     public void onError(Session session, Throwable error){
         System.out.println("发生错误");
         error.printStackTrace();
     }

}
