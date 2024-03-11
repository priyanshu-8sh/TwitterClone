package com.example.tweetService.service;

import com.example.tweetService.dto.request.TweetRequestDTO;
import com.example.tweetService.dto.response.TweetResponseDTO;

import java.util.List;

public interface ReplyService {
    TweetResponseDTO reply(TweetRequestDTO createTweetRequestDTO, Long replyToId,String loggedInUser);
    Boolean  deleteReply(Long replyId, String loggedInUser);
    TweetResponseDTO updateReply(Long replyId, TweetRequestDTO tweetRequestDTO,String loggedInUser);
    TweetResponseDTO getReply(Long replyId,String loggedInUser);
    List<TweetResponseDTO> getAllRepliesForUser(String profileId);
    List<TweetResponseDTO> getAllRepliesForTweet(Long replyToId, String loggedInUser);
}
