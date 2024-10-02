package org.bear.websocket.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bear.websocket.chat.ChatMsg;
import org.bear.websocket.chat.TypeMessage;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    // @EventListener
    // public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    //     log.info("Received a new web socket connection");
    // }
    private final SimpMessageSendingOperations messagingTemplate;


    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
         //log.info("Web socket connection disconnected");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
       String username = (String) headerAccessor.getSessionAttributes().get("username");//ON CASTE EN STRING CAR CES UN OBJET
        if(username != null) {
            log.info("User Disconnected : " + username);
            var chatMessage = ChatMsg.builder()
                    .content("User Disconnected")
                    .sender(username)
                    .type(TypeMessage.LEAVE)
                    .build();
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
        // TODO: Implement the method
    }
}
