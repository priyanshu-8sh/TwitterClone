package com.example.tweetService.service.Impl;

import com.example.clients.profile.ProfileServiceClient;
import com.example.clients.profile.dto.response.ProfileResponseDTO;
import com.example.tweetService.dao.TweetDao;
import com.example.tweetService.entity.Tweet;
import com.example.tweetService.dto.request.TweetRequestDTO;
import com.example.tweetService.dto.response.TweetResponseDTO;
import com.example.tweetService.mapper.TweetMapper;
import com.example.tweetService.service.ReplyService;
import com.example.tweetService.service.ViewService;
import com.example.tweetService.utils.TweetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private TweetDao tweetDao;
    @Autowired
    private ViewService viewService;
    @Autowired
    private ProfileServiceClient profileServiceClient;
    @Autowired
    TweetUtils tweetUtils;

    @Override
    public TweetResponseDTO reply(TweetRequestDTO createTweetRequestDTO, Long replyToId, String loggedInUser)
    {
        Tweet tweetDb=tweetDao.getTweet(replyToId);
        Tweet repliedTweet=TweetMapper.toEntity(createTweetRequestDTO, tweetDb, null, loggedInUser);
        tweetDao.createTweet(repliedTweet);
        TweetResponseDTO tweetResponseDTO=TweetMapper.toResponse(repliedTweet, loggedInUser);
        return tweetResponseDTO;
    }
    @Override
    public Boolean deleteReply(Long replyId, String loggedInUser)
    {
        Tweet replyTweet=tweetDao.getTweet(replyId);
        if(tweetUtils.isTweetOwnedByLoggedInUser(replyTweet, loggedInUser))
        {
            tweetDao.deleteTweet(replyId);
            return true;
        }
        else
        {
            return false;
//            throw new RuntimeException("You are not authorized to delete this tweet");
        }

    }

    @Override
    public TweetResponseDTO updateReply(Long replyId, TweetRequestDTO tweetRequestDTO,String loggedInUser)
    {
        TweetResponseDTO tweetResponseDTO=null;
        Tweet replyTweet=tweetDao.getTweet(replyId);
        if(tweetUtils.isTweetOwnedByLoggedInUser(replyTweet,loggedInUser))
        {
            Tweet updateTweet=TweetMapper.toEntity(tweetRequestDTO,null,null,loggedInUser);
            Tweet updatedTweet=tweetDao.updateTweet(replyId,updateTweet);
            tweetResponseDTO=TweetMapper.toResponse(updatedTweet, loggedInUser);
        }
        return tweetResponseDTO;

    }
    @Override
    public TweetResponseDTO getReply(Long replyId,String loggedInUser)
    {
        Tweet replyTweet=tweetDao.getReplyTweet(replyId);
        viewService.createViewEntity(replyTweet,loggedInUser);
        return TweetMapper.toResponse(replyTweet,loggedInUser);
    }
    @Override
    public List<TweetResponseDTO> getAllRepliesForUser(String profileId)
    {
        ProfileResponseDTO profile=profileServiceClient.getProfileById(profileId);
        List<Tweet> replyTweets=tweetDao.getAllRepliesForUser(profileId);

        List<TweetResponseDTO> tweetResponseDTOs = replyTweets.stream()
                .map(tweet -> TweetMapper.toResponse(tweet, profile.getEmail()))
                .collect(Collectors.toList());
        return tweetResponseDTOs;
    }

    @Override
    public List<TweetResponseDTO> getAllRepliesForTweet(Long replyToId, String loggedInUser)
    {
        Tweet replyToTweet=tweetDao.getTweet(replyToId);
        List<Tweet> repliesList = new ArrayList<>(replyToTweet.getReplies());
        List<TweetResponseDTO> tweetResponseDTOs = repliesList.stream()
                .map(tweet -> TweetMapper.toResponse(tweet, loggedInUser))
                .collect(Collectors.toList());
        return tweetResponseDTOs;
    }


}
