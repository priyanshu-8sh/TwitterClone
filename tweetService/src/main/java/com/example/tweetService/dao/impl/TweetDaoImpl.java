package com.example.tweetService.dao.impl;

import com.example.tweetService.dao.TweetDao;
import com.example.tweetService.entity.Like;
import com.example.tweetService.entity.Tweet;
import com.example.tweetService.repository.LikeRepository;
import com.example.tweetService.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TweetDaoImpl implements TweetDao {
    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private LikeRepository likeRepository;
     @Override
     public Tweet createTweet(Tweet tweet)
     {

         tweetRepository.saveAndFlush(tweet);
         return tweet;
     }
     @Override
     public Tweet updateTweet(long tweetId,Tweet UpdateTweet)
     {
         Optional<Tweet> tweetDb=tweetRepository.findById(tweetId);
            if(tweetDb.isPresent())
            {
                Tweet tweet=tweetDb.get();
                tweet.setText(UpdateTweet.getText());
//                tweet.setDeleted(true);
                tweetRepository.saveAndFlush(tweet);
                return tweet;
            }
            else
                throw new RuntimeException("Tweet not found");
     }
     @Override
     public Tweet getTweet(long tweetId)
    {
        Optional<Tweet> tweet=tweetRepository.findById(tweetId);
        if(tweet.isPresent())
            return tweet.get();
        else
            throw new RuntimeException("Tweet not found");

    }
    @Override
    public int CountRepliesToTweetById(long replyToId)
     {
         return tweetRepository.countAllByReplyToId(replyToId);
     }
     @Override
    public int CountRetweetToTweetById(long retweetToId)
     {
            return tweetRepository.countAllByRetweetToId(retweetToId);
     }
     @Override
     public  boolean isRetweetedByLoggedInUser(long retweetToId, String loggedInUser)
     {
         return tweetRepository.findByRetweetToIdAndProfileId(retweetToId,loggedInUser);
     }
     @Override
     public void deleteTweet(long tweetId)
     {
         tweetRepository.deleteById(tweetId);
         return;
     }

}
