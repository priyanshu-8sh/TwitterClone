package com.example.fanoutService.service.impl;

import com.example.fanoutService.service.KafkaService;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService {
    private Gson gson;

    @KafkaListener(topics = "fanout", groupId = "group_id")
    public void reciveMessage(String message) {
        System.out.println("Recieved message: " + message);
    }


}
