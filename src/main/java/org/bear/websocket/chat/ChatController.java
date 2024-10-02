package org.bear.websocket.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")//send to all subscribers
    public  ChatMsg sendMessage( @Payload  ChatMsg chatMessage) {
        return chatMessage;
    }


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")//send to all subscribers
    public ChatMsg addUser(@Payload ChatMsg chatMessage, SimpMessageHeaderAccessor headerAccessor) {

        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
