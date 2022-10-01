package com.example.UserService.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicKafka {

    @Bean
    public NewTopic userDeleteTopic(){
        return TopicBuilder.name("user_delete").build();
    }

    @Bean
    public NewTopic userNotifyTopic(){
        return TopicBuilder.name("user_notify").build();
    }
    @Bean
    public NewTopic userPostNotifyTopic(){
        return TopicBuilder.name("user_post_notify").build();
    }
}