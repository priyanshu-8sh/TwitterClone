package com.example.tweetService.service.Impl;

import com.example.tweetService.entity.Tweet;
import com.example.tweetService.service.KafkaService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.tweetService.dto.kafka.KafkaTweetMessage;

@Service
public class KafkaServiceImpl implements KafkaService {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    private Gson gson=new Gson();
    public void send(KafkaTweetMessage kafkaTweetMessage, String topic) {
        String msg = gson.toJson(kafkaTweetMessage);
        kafkaTemplate.send(topic, msg);
    }

    public void sendMessageToKafka(String topic, Tweet entity, String entityName, String operation) {
        KafkaTweetMessage kafkaTweetMessage = KafkaTweetMessage.builder()
                .entityId(entity.getId())
                .profileId(entity.getProfileId())
                .entityName(entityName)
                .operation(operation)
                .build();
        send(kafkaTweetMessage, topic);
    }
    @Override
    public void sendMessageWithTweetToKafka(Tweet tweet, String Operation)
    {
        sendMessageToKafka("user-timeline",tweet,"tweets","ADD");
        return;
    }


}
