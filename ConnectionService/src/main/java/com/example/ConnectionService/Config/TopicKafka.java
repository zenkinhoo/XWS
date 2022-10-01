package com.example.ConnectionService.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.apache.kafka.clients.admin.NewTopic;
@Configuration
public class TopicKafka {
    @Bean
    public NewTopic userDeleteTopic(){
        return TopicBuilder.name("user_delete").build();
    }
    @Bean
    public NewTopic userUpdateTopic(){
        return TopicBuilder.name("user_update").build();
    }
    @Bean
    public NewTopic userNotifyTopic(){
        return TopicBuilder.name("user_notify").build();
    }
}