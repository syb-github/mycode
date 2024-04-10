package com.bing.rabbitmqtest.middleware.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunyibing
 * @date 2024/4/10
 */
@Slf4j
@Controller
@ServerEndpoint(value = "/websocket/{sid}")
public class WebSocketServer {
    /**
     * 静态变量  存储当前线程连接数
     */
    private static AtomicInteger inlineSessionCount = new AtomicInteger(0);


    /**
     * 存储在线客户端
     */
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    private String sid;
    private Session session1;

    /**
     * 连接建立成功调用的方法。由前端<code>new WebSocket</code>触发
     * @param sid
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("sid") String sid, Session session){
        log.info("连接建立中 ==> session_id = {}， sid = {}", session.getId(), sid);
        sessionMap.put(sid, session);
        // 在线数加1
        inlineSessionCount.incrementAndGet();
        this.sid = sid;
        this.session1 = session;
        sendToOne(sid, "连接成功");
        log.info("连接建立成功，当前在线数为：{} ==> 开始监听新连接：session_id = {}， sid = {},。", inlineSessionCount, session.getId(), sid);
    }

    /**
     * 连接关闭调用的方法。由前端<code>socket.close()</code>触发
     * @param sid
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid, Session session){
        // 从 Map中移除
        sessionMap.remove(sid);

        //在线数减1
        inlineSessionCount.decrementAndGet();
        log.info("连接关闭成功，当前在线数为：{} ==> 关闭该连接信息：session_id = {}， sid = {},。", inlineSessionCount, session.getId(), sid);
    }

    /**
     *  收到客户端消息后调用的方法。由前端<code>socket.send</code>触发
     *   当服务端执行toSession.getAsyncRemote().sendText(xxx)后，前端的socket.onmessage得到监听。
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session){
        /**
         * html界面传递来得数据格式，可以自定义.
         * {"sid":"user-1","message":"hello websocket"}
         */

        JSONObject jsonObject = JSONUtil.parseObj(message);
        String toSid = jsonObject.getStr("sid");
        String msg = jsonObject.getStr("message");
        log.info("服务端收到客户端消息 ==> fromSid = {}, toSid = {}, message = {}", sid, toSid, message);

        //模拟约定：如果未指定sid信息，则群发，否则就单独发送
        if (toSid.isEmpty()) {
            sendToAll(msg);
        } else {
            sendToOne(toSid, msg);
        }
    }

    /**
     * 发生错误调用的方法
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        log.error("WebSocket发生错误，错误信息为：" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 群发消息
     *
     * @param message 消息
     */
    private void sendToAll(String message) {
        // 遍历在线map集合
        sessionMap.forEach((onlineSid, toSession) -> {
            // 排除掉自己
            if (!sid.equalsIgnoreCase(onlineSid)) {
                log.info("服务端给客户端群发消息 ==> sid = {}, toSid = {}, message = {}", sid, onlineSid, message);
                toSession.getAsyncRemote().sendText(message);
            }
        });
    }

    /**
     * 给指定客户端发送消息
     * @param sid
     * @param message
     */
    private void sendToOne(String sid, String message){
        Session session = sessionMap.get(sid);
        if(session == null){
            log.error("服务端给客户端发送消息 ==> toSid = {} 不存在, message = {}", sid, message);
            return;
        }
        // 异步发送
        log.info("服务端给客户端发送消息 ==> toSid = {}, message = {}", sid, message);
        session.getAsyncRemote().sendText(message);
    }
}
