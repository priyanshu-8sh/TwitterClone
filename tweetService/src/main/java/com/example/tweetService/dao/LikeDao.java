package com.example.tweetService.dao;

import org.springframework.stereotype.Service;

public interface LikeDao {
    void likeTweet(long tweetId,String loggedInUser);
    void UnlikeTweet(long tweetId,String loggedInUser);
    int CountLikesForTweet(long tweetId);
    boolean isLikedByloggedInUser(long tweetId,String loggedInUser);
}
