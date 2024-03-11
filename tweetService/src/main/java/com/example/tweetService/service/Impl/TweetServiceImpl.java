package com.example.tweetService.service.Impl;


import com.example.clients.profile.ProfileServiceClient;
import com.example.tweetService.dao.TweetDao;
import com.example.tweetService.dto.request.TweetRequestDTO;
import com.example.tweetService.dto.request.UpdateTweetRequestDTO;
import com.example.tweetService.dto.response.TweetResponseDTO;
import com.example.tweetService.entity.Tweet;
import com.example.tweetService.mapper.TweetMapper;
import com.example.tweetService.service.ClientService;
import com.example.tweetService.service.KafkaService;
import com.example.tweetService.service.TweetService;
import com.example.tweetService.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    ProfileServiceClient profileServiceClient;
    @Autowired
    TweetDao tweetDao;

    @Autowired
    ViewService viewService;
    @Autowired
    KafkaService kafkaService;
    @Override
    public TweetResponseDTO createTweet(TweetRequestDTO createTweetRequestDTO, String loggedInUser)
    {


        return Optional.of(createTweetRequestDTO)
                .map(request-> {
                    return TweetMapper.toEntity(request, null, null, loggedInUser);
                })
                .map(tweet->tweetDao.createTweet(tweet))
                .map(tweet->{
                    kafkaService.sendMessageWithTweetToKafka(tweet, "ADD");
                    return tweet;
                })
                //add kafka storing of tweets here
                .map(tweet->{
                    return TweetMapper.toResponse(tweet, loggedInUser);
                })
                .orElseThrow(()->new RuntimeException("Error while creating tweet"));
    }
    @Override
    public TweetResponseDTO createQuoteTweet(TweetRequestDTO createTweetRequestDTO, Long tweetId, String loggedInUser)
    {
        Tweet quoteTweet= tweetDao.getTweet(tweetId);
        Tweet newTweet=TweetMapper.toEntity(createTweetRequestDTO, quoteTweet, null, loggedInUser);
        tweetDao.createTweet(newTweet);
        //add kafka storing of tweets here
        return TweetMapper.toResponse(newTweet, loggedInUser);
    }
    @Override
    public TweetResponseDTO getTweetById(Long tweetId, String loggedInUser)
    {
        Tweet tweet=tweetDao.getTweet(tweetId);
        viewService.createViewEntity(tweet, loggedInUser);
        return TweetMapper.toResponse(tweet, loggedInUser);

    }
    @Override
    public TweetResponseDTO updateTweet(Long tweetId, TweetRequestDTO updateTweetRequestDTO,String loggedInUser)
    {

        Tweet newTweet=TweetMapper.toEntity(updateTweetRequestDTO, null, null,loggedInUser);
        Tweet tweet=tweetDao.updateTweet(tweetId,newTweet);
        return TweetMapper.toResponse(tweet, loggedInUser);

    }
    @Override
    public Boolean deleteTweet(Long tweetId, String loggedInUser)
    {
        String profileId=profileServiceClient.getProfileIdByLoggedInUser(loggedInUser);
        if(tweetDao.getTweet(tweetId).getProfileId().equals(profileId)) //if tweet is owned by the login user or not
        {
            tweetDao.deleteTweet(tweetId);
            return true;
        }
        else
            return false;
    }


}
