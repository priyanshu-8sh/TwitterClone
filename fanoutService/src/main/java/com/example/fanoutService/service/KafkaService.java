package com.example.fanoutService.service;

public interface KafkaService {
    void reciveMessageFromUserTopic(String message);
    void reciveMessageFromHomeTopic(String message);
}
