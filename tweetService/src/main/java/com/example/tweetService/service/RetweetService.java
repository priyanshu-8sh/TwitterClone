package com.example.tweetService.service;

import com.example.tweetService.dto.response.TweetResponseDTO;

import java.util.List;

public interface RetweetService {
    Boolean retweet(Long tweetId,String loggedInUser);
    Boolean deleteRetweet(Long tweetId,String loggedInUser);

    TweetResponseDTO getRetweetById(Long retweetId, String loggedInUser);
    List<TweetResponseDTO> getAllRetweetsForUser(String profileId);

}
