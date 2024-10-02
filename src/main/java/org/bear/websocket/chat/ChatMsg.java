package org.bear.websocket.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChatMsg {
    private String content;
    private String sender;
    private  TypeMessage type;
}
