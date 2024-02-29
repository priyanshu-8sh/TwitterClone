package com.example.tweetService.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "view",
        uniqueConstraints = @UniqueConstraint(columnNames = {"parent_tweet_id", "profileId"}),
        indexes = {
                @Index(columnList = "parent_tweet_id")
        })
public class View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String profileId;
    @ManyToOne(targetEntity = Tweet.class)
    private Tweet parentTweet;
}
