package com.example.tweetService.dao.impl;

import com.example.tweetService.dao.LikeDao;
import com.example.tweetService.entity.Like;
import com.example.tweetService.entity.Tweet;
import com.example.tweetService.repository.LikeRepository;
import com.example.tweetService.repository.TweetRepository;
import com.example.tweetService.mapper.LikeMapper;
import com.example.clients.profile.ProfileServiceClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

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
        //saves the new like to the tweet
        Tweet tweet=tweetDB.get();
        Set<Like> likes=tweet.getLikes();
        likes.add(like);
        tweet.setLikes(likes);
        tweetRepository.saveAndFlush(tweet);


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
        //removes the like from the tweet
        Optional<Tweet>tweetDb=tweetRepository.findById(tweetId);
        if(!tweetDb.isPresent())
            throw new RuntimeException("Tweet not found");
        Tweet tweet=tweetDb.get();
        Set<Like> likes=tweet.getLikes();
        likes.remove(like.get());
        tweet.setLikes(likes);
        tweetRepository.saveAndFlush(tweet);
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
