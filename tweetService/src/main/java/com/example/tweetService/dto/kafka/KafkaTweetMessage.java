package com.example.tweetService.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaTweetMessage {
    private Long entityId;//the id of tweet ->tweet,reply, retweet
    private String profileId; //the id of user did that
    private String entityName; //tweet,reply,retweet`
    private String operation; //ADD or DELETE
}
