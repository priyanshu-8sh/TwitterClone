package com.example.tweetService.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tweet",
uniqueConstraints = {
        @UniqueConstraint(columnNames = {"profileId","retweet_to_id"}, name = "uniqueConstraintTweet")
},
indexes = {
        @Index(columnList = "reply_to_id"),
        @Index(columnList = "retweet_to_id"),
        @Index(columnList = "quote_to_id")})
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String profileId;
    private LocalDateTime creationDate;
    @OneToMany(targetEntity = Like.class,mappedBy = "parentTweet", cascade = CascadeType.ALL)
    private Set<Like> likes = new HashSet<>();
    @OneToMany(targetEntity = Tweet.class,mappedBy = "retweetTo", cascade = CascadeType.ALL)
    private Set<Tweet> retweets = new HashSet<>();
    @OneToMany(targetEntity = Tweet.class,mappedBy = "replyTo", cascade = CascadeType.ALL)
    private Set<Tweet> replies = new HashSet<>();
    @OneToMany(mappedBy = "parentTweet", cascade = CascadeType.ALL)
    private Set<View> views = new HashSet<>();
    @ManyToOne(targetEntity = Tweet.class)
    @Nullable
    private Tweet retweetTo;
    @ManyToOne(targetEntity = Tweet.class)
    @Nullable
    private Tweet replyTo;
    @ManyToOne(targetEntity = Tweet.class)
    @Nullable
    private Tweet quoteTo;

}
