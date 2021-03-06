package com.example.demo.service;

import com.example.demo.model.Constant;
import com.example.demo.model.LiveInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 广播
     * 发给所有在线用户
     *
     * @param msg
     */
    public void sendMsg(LiveInfo msg) {
        template.convertAndSend("/topic/greetings", msg);
    }

    /**
     * 发送给指定用户
     * @param users
     * @param msg
     */
    public void send2Users(List<String> users, LiveInfo msg) {
        users.forEach(userName -> {
            template.convertAndSendToUser(userName, "/topic/greetings", msg);
        });
    }
}