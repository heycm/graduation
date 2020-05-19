package com.heycm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Description webSocket 配置
 * @Author heycm@qq.com
 * @Date 2020-04-25 22:33
 */

// @Configuration
// @EnableWebSocketMessageBroker
// public class WebSocketConfig2 implements WebSocketMessageBrokerConfigurer {
//
//     /**
//      * websocket服务地址节点
//      * @param registry
//      */
//     @Override
//     public void registerStompEndpoints(StompEndpointRegistry registry) {
//         // 可以添加多个节点
//         registry.addEndpoint("/ws/stomp").withSockJS();
//     }
//
//     @Override
//     public void configureMessageBroker(MessageBrokerRegistry registry) {
//         registry.enableSimpleBroker("/queue", "/topic");
//         registry.setApplicationDestinationPrefixes("/app");
//         registry.setUserDestinationPrefix("/user");
//     }
// }
