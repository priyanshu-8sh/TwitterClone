package com.example.tweetService.service;

import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    void likeTweet(Long tweetId,String loggedInUser);
    void unlikeTweet(Long tweetId,String loggedInUser);
}
