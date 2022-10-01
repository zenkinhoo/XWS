package com.example.ConnectionService;

import com.example.ConnectionService.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @Autowired
    private ChatService chatService;

    @KafkaListener(
            topics = "user_delete",
            groupId = "connection_service",
            containerFactory = "userDeleteKafkaListenerContainerFactory"
    )
    void userDeleteListener(String userId){
        chatService.deleteUserMessages(userId);
    }
}
