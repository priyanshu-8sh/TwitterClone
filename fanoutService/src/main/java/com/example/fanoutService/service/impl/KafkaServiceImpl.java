package com.example.fanoutService.service.impl;

import com.example.fanoutService.service.KafkaService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class KafkaServiceImpl implements KafkaService {
    private Gson gson;

    @Override
    @KafkaListener(topics = "user-timeline", groupId = "group_id")
    public void reciveMessageFromUserTopic(String message) {
        log.info("Recieved message: " + message);

    }
    @Override
    @KafkaListener(topics = "home-timeline", groupId = "group_id")
    public void reciveMessageFromHomeTopic(String message) {
        System.out.println("Recieved message: " + message);
    }


}
