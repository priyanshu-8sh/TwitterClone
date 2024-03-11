package com.example.tweetService.dao.impl;

import com.example.tweetService.dao.LikeDao;
import com.example.tweetService.entity.Like;
import com.example.tweetService.entity.Tweet;
import com.example.tweetService.repository.LikeRepository;
import com.example.tweetService.repository.TweetRepository;
import com.example.tweetService.mapper.LikeMapper;
import com.example.clients.profile.ProfileServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
@Service
public class LikeDaoImpl implements LikeDao {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private ProfileServiceClient profileServiceClient;

    @Override
    public void likeTweet(long tweetId,String loggedInUser){
        String profileId= profileServiceClient.getProfileIdByLoggedInUser(loggedInUser);
        Optional<Tweet> tweetDB=tweetRepository.findById(tweetId);
        if(!tweetDB.isPresent())
            throw new RuntimeException("Tweet not found");

        Like like=LikeMapper.toLike(tweetDB.get(),profileId);
        likeRepository.saveAndFlush(like);
        //when we'll extract the tweet the like will be automatically added in it
        return;
    }
    @Override
    public void UnlikeTweet(long tweetId,String loggedInUser){

        String profileId= profileServiceClient.getProfileIdByLoggedInUser(loggedInUser);
        Optional<Like> like = likeRepository.findByParentTweetIdAndProfileId(tweetId,profileId);
        if(like.isPresent())
        {
            likeRepository.delete(like.get());
        }
        else
            throw new RuntimeException("Like not found");
    }

    @Override
    public int CountLikesForTweet(long tweetId)
    {
        return likeRepository.countAllByParentTweetId(tweetId);
    }
    @Override
    public boolean isLikedByloggedInUser(long tweetId,String loggedInUser)
    {
        String profileId=profileServiceClient.getProfileIdByLoggedInUser(loggedInUser);
        return likeRepository.findByParentTweetIdAndProfileId(tweetId,profileId).isPresent();
    }
}
