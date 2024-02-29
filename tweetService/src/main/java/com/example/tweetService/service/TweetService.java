package com.example.tweetService.service;

import com.example.tweetService.dto.request.TweetRequestDTO;
import com.example.tweetService.dto.response.TweetResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface TweetService {
    TweetResponseDTO createTweet(TweetRequestDTO createTweetRequestDTO, String loggedInUser);

    TweetResponseDTO createQuoteTweet(TweetRequestDTO createTweetRequestDTO, Long tweetId, String loggedInUser);
    TweetResponseDTO getTweetById(Long tweetId, String loggedInUser);

    TweetResponseDTO updateTweet( Long tweetId,TweetRequestDTO updateTweetRequestDTO, String loggedInUser);
      Boolean deleteTweet(Long tweetId, String loggedInUser);

}
