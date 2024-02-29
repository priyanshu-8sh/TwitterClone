package com.example.tweetService.repository;

import com.example.tweetService.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    int countAllByReplyToId(long replyToId);

    int countAllByRetweetToId(long retweetToId);

    boolean findByRetweetToIdAndProfileId(long retweetToId, String loggedInUser);
}
