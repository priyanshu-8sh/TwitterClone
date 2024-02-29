package com.example.tweetService.service.Impl;

import com.example.tweetService.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.tweetService.dao.LikeDao;
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeDao likeDao;
    @Override
    public void likeTweet(Long tweetId,String loggedInUser)
    {
        likeDao.likeTweet(tweetId,loggedInUser);
        return;
    }
    @Override
    public void unlikeTweet(Long tweetId,String loggedInUser)
    {
        likeDao.UnlikeTweet(tweetId,loggedInUser);
        return;
    }
}
