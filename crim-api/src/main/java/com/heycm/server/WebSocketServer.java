package com.heycm.server;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heycm.config.JwtToken;
import com.heycm.dto.ChatDTO;
import com.heycm.enums.CommEnum;
import com.heycm.model.Chat;
import com.heycm.model.User;
import com.heycm.service.IChatService;
import com.heycm.service.IUserService;
import com.heycm.utils.JwtUtil;
import com.heycm.utils.date.DateUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private static IChatService chatService;
    private static IUserService userService;
    private static JwtUtil jwtUtil;

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        WebSocketServer.jwtUtil = jwtUtil;
    }
    @Autowired
    @Lazy
    public void setChatService(IChatService chatService) {
        WebSocketServer.chatService = chatService;
    }
    @Autowired
    public void setUserService(IUserService userService) {
        WebSocketServer.userService = userService;
    }

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
        log.info("[服务器WebSocket初始化][当前时间:{}][结束]", DateUtil.getStringYMDHMS());
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String userId, Session session) throws IOException {
        // 设置当前窗口id和session
        User user = userService.getById(Integer.valueOf(userId));
        if (user.getPid()!=null) {
            userId = user.getPid().toString();
        }
        this.userId = userId;
        this.session = session;
        // 将当期会话收入websocketMap
        if (websocketMap.get(userId) != null){
            websocketMap.remove(userId);
        }
        websocketMap.put(this.userId, this);
        // 在线会话+1
        addOnlineCount();
        log.info("[窗口加入][窗口:{}][在线数量:{}][结束]", this.userId, getOnlineCount());
        // 连接成功，返回状态
        sendMessage(Result.ok(CommEnum.WS_CONN_SUCCESS));
        // 发送未接收的消息
        chatService.sendNotReceived(Integer.valueOf(this.userId));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (websocketMap.get(this.userId) != null) {
            websocketMap.remove(this.userId);
            subOnlineCount();
            log.info("[窗口关闭][窗口:{}][在线数量:{}][结束]", this.userId, getOnlineCount());
        }
    }

    /**
     * 收到客户端消息调用的方法
     *
     * @param message 客户端发送的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("[收到消息][窗口:{}][消息:{}][结束]",this.userId, message);
    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("[发生错误:{}]][窗口:{}][结束]", error.getMessage(), this.userId);
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
     * @param
     * @throws IOException
     */
    public void sendAll(Chat chat) throws IOException {

    }

    /**
     * 指定Session发送消息
     * message进来前需要进行必要验证过滤
     *
     * @param
     * @throws IOException
     */
    public ResponseMessage sendOne(ChatDTO chatDTO) throws IOException {
        // 找到接受者的窗口
        WebSocketServer to = websocketMap.get(chatDTO.getToId().toString());
        // 认为对方不在线
        if(to == null){
            return Result.ok(CommEnum.WS_NOT_ONLINE);
        }
        // 认为对方在线,发送消息
        to.sendMessage(chatDTO);
        return Result.ok(CommEnum.WS_SEND_SUCCESS);
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
