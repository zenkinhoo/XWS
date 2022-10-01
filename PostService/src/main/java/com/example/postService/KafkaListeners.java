package com.example.postService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.postService.Service.PostService;

@Component
public class KafkaListeners {
    @Autowired
    private PostService postService;

    @KafkaListener(
            topics = "user_delete",
            groupId = "post_service",
            containerFactory = "userDeleteKafkaListenerContainerFactory"
    )
    void userDeleteListener(String userId){
        postService.deleteUserPosts(userId);
        postService.deleteUserComments(userId);
    }

}
