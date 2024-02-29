package com.example.tweetService.mapper;

import com.example.clients.profile.ProfileServiceClient;
import com.example.tweetService.entity.Tweet;
import com.example.tweetService.entity.Like;
import org.springframework.beans.factory.annotation.Autowired;

public class LikeMapper {
    @Autowired
    private static ProfileServiceClient profileServiceClient;
    public static Like toLike(Tweet tweet, String profileId)
    {
        Like like = new Like();
        //setting the tweet on which like has happened
        like.setParentTweet(tweet);
        //setting the user who liked the tweet
        like.setProfileId(profileId);
        return like;

    }

}
