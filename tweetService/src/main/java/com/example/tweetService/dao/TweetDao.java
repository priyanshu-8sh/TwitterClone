package com.example.tweetService.dao;

import com.example.tweetService.entity.Tweet;

public interface TweetDao {
    Tweet createTweet(Tweet tweet);
    Tweet updateTweet(long tweetId,Tweet tweet);
    Tweet getTweet(long tweetId);
    int CountRepliesToTweetById(long replyToId);
    int CountRetweetToTweetById(long retweetToId);

    boolean isRetweetedByLoggedInUser(long retweetToId, String loggedInUser);

    void deleteTweet(long tweetId);

}
