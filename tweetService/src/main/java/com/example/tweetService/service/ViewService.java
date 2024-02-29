package com.example.tweetService.service;

import com.example.tweetService.entity.Tweet;
import com.example.tweetService.entity.View;
import org.springframework.stereotype.Service;

@Service
public interface ViewService {
    View createViewEntity(Tweet tweet, String loggedInUser);
}
