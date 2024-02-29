package com.example.tweetService.mapper;

import com.example.clients.profile.ProfileServiceClient;
import com.example.tweetService.dao.LikeDao;
import com.example.tweetService.dao.TweetDao;
import com.example.tweetService.dto.response.TweetResponseDTO;
import com.example.tweetService.entity.Tweet;
import com.example.tweetService.dto.request.TweetRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public  class TweetMapper {
    @Autowired
    private static ProfileServiceClient profileServiceClient;
    @Autowired
    private static LikeDao likeDao;
    @Autowired
    private static TweetDao tweetDao;
    public static Tweet toEntity(TweetRequestDTO createTweetRequestDTO, Tweet quoteTo, Tweet replyTo, String loggedInUser) {
        Tweet tweet = new Tweet();
        tweet.setText(createTweetRequestDTO.getText());
        tweet.setProfileId(profileServiceClient.getProfileIdByLoggedInUser(loggedInUser));
        tweet.setCreationDate(LocalDateTime.now());
        tweet.setReplyTo(replyTo);
        tweet.setQuoteTo(quoteTo);
        return tweet;
    }
    public static TweetResponseDTO toResponse(Tweet tweet,String loggedInUser){
        if(tweet == null) {
            return null;
        }
        TweetResponseDTO tweetResponseDTO = new TweetResponseDTO();
        tweetResponseDTO.setId(tweet.getId());
        tweetResponseDTO.setCreationDate(tweet.getCreationDate());
        tweetResponseDTO.setReplyTo(toResponse(tweet.getReplyTo(),loggedInUser));
        tweetResponseDTO.setRetweetTo(toResponse(tweet.getRetweetTo(),loggedInUser));
        tweetResponseDTO.setQuoteTo(toResponse(tweet.getQuoteTo(),loggedInUser));
        tweetResponseDTO.setProfile(profileServiceClient.getProfileById(tweet.getProfileId()));
        tweetResponseDTO.setText(tweet.getText());
        tweetResponseDTO.setLikes(likeDao.CountLikesForTweet(tweet.getId()));
        tweetResponseDTO.setReplies(tweetDao.CountRepliesToTweetById(tweet.getId()));
        tweetResponseDTO.setRetweets(tweetDao.CountRetweetToTweetById(tweet.getId()));
        tweetResponseDTO.setViews(100);
        tweetResponseDTO.setIsLiked(likeDao.isLikedByloggedInUser(tweet.getId(),loggedInUser));
        //This does not necessarily mean that the tweet with the ID tweet.getId() is a retweet itself. It could be an original tweet that has been retweeted by others
        tweetResponseDTO.setIsRetweeted(tweetDao.isRetweetedByLoggedInUser(tweet.getId(),loggedInUser));
        tweetResponseDTO.setIsBelongs(profileServiceClient.getProfileById(tweet.getProfileId()).getEmail().equals(loggedInUser));


        return tweetResponseDTO;
    }

//    public boolean isTweetLikedByLoggedInUser(Long parentTweetId, String loggedInUser, ProfileServiceClient profileServiceClient) {
//        String profileIdOfLoggedInUser = profileServiceClient.getProfileIdByLoggedInUser(loggedInUser);
//        return LikeDao.findByParentTweetIdAndProfileId(parentTweetId, profileIdOfLoggedInUser).isPresent();
//    }
}
