package com.may.websocket;

import org.apache.catalina.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Date;

import static org.springframework.messaging.simp.stomp.StompHeaders.SESSION;

@Controller
public class MessageController {

    @MessageMapping("on") // api 매핑
    @SendTo("/chat/on")
    public Message on(Message message) throws InterruptedException {
        Thread.sleep(100);
        return message;
    }

    @MessageMapping("chat") // api 매핑
    @SendTo("/chat/message")
    public Message chat(Message message) throws InterruptedException {
        Thread.sleep(100);
        System.out.println(message.getContent());
        message.setSendDate(new Date());
        return message;
    }

    @MessageMapping("off") // api 매핑
    @SendTo("/chat/off")
    public Message off(Message message) throws InterruptedException {
        Thread.sleep(100);
        return message;
    }



//    @MessageMapping("chat") // api 매핑
//    @SendTo("/topic/message")
//    public String chat(String message, SimpMessageHeaderAccessor messageHeaderAccessor){
//        User talker = messageHeaderAccessor.getSessionAttributes().get(SESSION).get(USER_SESSION_KEY);
//        if(talker == null) throw new Exception("로그인한 사용자만 채팅에 참여할 수 있습니다");
//        return message;
//    }

}
