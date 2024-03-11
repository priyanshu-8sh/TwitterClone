package com.example.tweetService.service.Impl;

import com.example.tweetService.dao.TweetDao;
import com.example.tweetService.dto.response.TweetResponseDTO;
import com.example.tweetService.service.RetweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetweetServiceImpl implements RetweetService {
    @Autowired
    private TweetDao tweetDao;

    @Override
    public Boolean retweet(Long tweetId, String loggedInUser) {
        return null;
    }

    @Override
    public Boolean deleteRetweet(Long tweetId, String loggedInUser) {
        return null;
    }

    @Override
    public TweetResponseDTO getRetweetById(Long retweetId, String loggedInUser) {
        return null;
    }

    @Override
    public List<TweetResponseDTO> getAllRetweetsForUser(String profileId) {
        return null;
    }
}
