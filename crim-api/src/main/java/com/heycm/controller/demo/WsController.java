package com.heycm.controller.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-04-25 22:38
 */
@Controller
public class WsController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    // @MessageMapping("/chat")
    // public void handleChat(Chat chat) {
    //     Subject subject = SecurityUtils.getSubject();
    //     chat.setFrom((String) subject.getPrincipal());
    //     simpMessagingTemplate.convertAndSendToUser(chat.getTo(), "/queue/chat", chat);
    // }

    @RequestMapping("/ws2")
    public String ws2() {
        return "ws/ws2";
    }
}
