package com.example.tweetService.service;

import com.example.tweetService.entity.Tweet;

public interface KafkaService {

    void sendMessageWithTweetToKafka(Tweet tweet, String Operation);
}
