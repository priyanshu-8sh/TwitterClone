package com.example.tweetService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"parent_tweet_id", "profileId"}),
        indexes = {
                @Index(columnList = "parent_tweet_id")
        })
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String profileId;
    @ManyToOne(targetEntity = Tweet.class)
    private Tweet parentTweet;
}
