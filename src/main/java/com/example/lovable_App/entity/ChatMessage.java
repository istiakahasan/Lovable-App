package com.example.lovable_App.entity;

import com.example.lovable_App.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {

    Long id;
    ChatSession chatSession;
    String content;

    MessageRole messageRole;
    String toolCalls;//Json array of tools
    Integer tokenUsed;
    Instant createdAt;


}
