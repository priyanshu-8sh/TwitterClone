package com.example.tweetService.service.Impl;

import com.example.clients.profile.ProfileServiceClient;
import com.example.tweetService.dao.ViewDao;
import com.example.tweetService.entity.Tweet;
import com.example.tweetService.entity.View;
import com.example.tweetService.mapper.ViewMapper;
import com.example.tweetService.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;

public class ViewServiceImpl implements ViewService {
    @Autowired
    ViewDao viewDao;

    @Autowired
    ProfileServiceClient profileServiceClient;
    @Override
    public View createViewEntity(Tweet tweet, String loggedInUser)
    {
        String profileID=profileServiceClient.getProfileIdByLoggedInUser(loggedInUser);
        View viewDB=viewDao.findByTweetIdAndProfileId(tweet.getId(),profileID);
        if(viewDB==null)
        {
            View view=ViewMapper.toEntity(tweet,loggedInUser);
            viewDao.createView(view);
        }
        return viewDB;
    }

}
