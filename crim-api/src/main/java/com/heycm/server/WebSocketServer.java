package com.heycm.server;

import com.alibaba.fastjson.JSON;
import com.heycm.model.ws.WsMessage;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-03-21 15:23
 */

@ServerEndpoint(value = "/ws/{userId}")
@Component
public class WebSocketServer {

    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    // private static RedisUtil redisUtil;
    //
    // @Autowired
    // public void setRedisUtil(RedisUtil redisUtil){
    //     WebSocketServer.redisUtil = redisUtil;
    // }

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //使用map对象，便于根据userId来获取对应的WebSocket
    private static ConcurrentHashMap<String, WebSocketServer> websocketMap = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private String userId = "";


    @PostConstruct
    public void init() {
        System.out.println("====== websocket 加载...");
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String userId, Session session) {
        // 设置当前窗口id和session
        this.userId = userId;
        this.session = session;

        // 将当期会话收入websocketMap
        websocketMap.put(userId, this);
        addOnlineCount();
        log.info("有连接加入，userId:{}，当前连接数为:{}", this.userId, getOnlineCount());
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", userId);
            map.put("data", "连接成功");
            // 连接成功，返回状态给浏览器
            sendMessage(map);

            // TODO 查询未接收的消息列表，返回给窗口


        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (websocketMap.get(this.userId) != null) {
            websocketMap.remove(userId);
            subOnlineCount();
            log.info("有连接关闭，userId:{}，当前连接数为:{}", this.userId, getOnlineCount());
        }

    }

    /**
     * 收到客户端消息调用的方法
     *
     * @param message 客户端发送的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + this.userId + "的信息:" + message);

    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误:{}, userId:{}", error.getMessage(), this.userId);
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(Object message) throws IOException {
        this.session.getBasicRemote().sendText(JSON.toJSONString(message));
    }

    /**
     * 群发消息
     * @param message
     * @throws IOException
     */
    public void sendAll(WsMessage message) throws IOException {

    }

    /**
     * 指定Session发送消息
     * message进来前需要进行必要验证过滤
     *
     * @param message 消息实体
     * @throws IOException
     */
    public ResponseMessage sendOne(WsMessage message) throws IOException {
        // TODO message进来前需要进行必要信息验证过滤，userId，friendId，message非空，friendId是否存在数据库等

        String friendId = message.getFriendId();
        WebSocketServer friendServer = websocketMap.get(friendId);
        if (friendServer != null) {
            // 接收方在线
            friendServer.sendMessage(message);
            log.info("发送成功");
            return Result.ok("发送成功");
        }
        // 接收方不在线
        // TODO 进行存盘操作，等接收方上线再发送

        return Result.ok("接收方未在线，消息已存盘");
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
