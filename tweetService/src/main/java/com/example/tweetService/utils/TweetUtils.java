package com.example.tweetService.utils;

import com.example.tweetService.entity.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.clients.profile.ProfileServiceClient;
@Component
public class TweetUtils {
    @Autowired
    ProfileServiceClient profileServiceClient;
    public Boolean isTweetOwnedByLoggedInUser(Tweet tweet, String loggedInUser) {

        return tweet.getProfileId().equals(profileServiceClient.getProfileIdByLoggedInUser(loggedInUser))?true:false;
    }

}
