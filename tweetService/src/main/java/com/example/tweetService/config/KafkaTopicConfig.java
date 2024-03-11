package com.example.tweetService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic userTimeLineTopic(){
        return TopicBuilder.name("user-timeline")
//                .partitions(3)
//                .replicas(3)
                .build();
    }
    public NewTopic homeTimeLineTopic(){
        return TopicBuilder.name("home -timeline")
//                .partitions(3)
//                .replicas(3)
                .build();
    }
}
