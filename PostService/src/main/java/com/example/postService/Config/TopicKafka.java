package com.example.postService.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class TopicKafka {
    @Bean
    public NewTopic userDeleteTopic(){
        return TopicBuilder.name("user_delete").build();
    }
    @Bean
    public NewTopic userPostNotifyTopic(){
        return TopicBuilder.name("user_post_notify").build();
    }


}
