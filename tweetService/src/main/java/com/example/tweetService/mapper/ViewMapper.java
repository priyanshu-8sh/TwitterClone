package com.example.tweetService.mapper;

import com.example.clients.profile.ProfileServiceClient;
import com.example.tweetService.entity.Tweet;
import com.example.tweetService.entity.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewMapper {

    @Autowired
    private static ProfileServiceClient profileServiceClient;

    public static View toEntity(Tweet tweet, String loggedInUser) {
        View view = new View();
        view.setParentTweet(tweet);
        view.setProfileId(profileServiceClient.getProfileIdByLoggedInUser(loggedInUser));
        return view;

    }
}
