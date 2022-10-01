package com.example.UserService.Config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration

public class ConsumerKafka {
    public Map<String, Object> userNotifyConfig() {
        HashMap<java.lang.String, java.lang.Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> userNotifyConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(userNotifyConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> userNotifyKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userNotifyConsumerFactory());
        return factory;
    }

    public Map<String, Object> userPostNotifyConfig() {
        HashMap<java.lang.String, java.lang.Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> userPostNotifyConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(userPostNotifyConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> userPostNotifyKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userPostNotifyConsumerFactory());
        return factory;
    }
}
