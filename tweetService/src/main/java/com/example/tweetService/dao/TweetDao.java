package com.example.tweetService.dao;

import com.example.tweetService.entity.Tweet;

import java.util.List;

public interface TweetDao {
    Tweet createTweet(Tweet tweet);
    Tweet updateTweet(Long tweetId,Tweet UpdateTweet);
    Tweet getTweet(Long tweetId);
    Tweet getReplyTweet(Long replyToId);
    int CountRepliesToTweetById(Long replyToId);
    int CountRetweetToTweetById(Long retweetToId);
    boolean isRetweetedByLoggedInUser(Long retweetToId, String loggedInUser);
    void deleteTweet(Long tweetId);
    List<Tweet> getAllRepliesForUser(String profileId);

}
