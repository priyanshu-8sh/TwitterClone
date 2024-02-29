package com.example.tweetService.controller;

import com.example.tweetService.dto.request.TweetRequestDTO;
import com.example.tweetService.dto.response.TweetResponseDTO;
import com.example.tweetService.service.TweetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tweets")
public class TweetController {
    @Autowired
    TweetService tweetService;

    @PostMapping("/tweet")
    ResponseEntity<TweetResponseDTO> createTweet(@RequestBody @Valid TweetRequestDTO createTweetRequestDTO,
                                                 @RequestHeader String loggedInUser) {
        return ResponseEntity.ok(tweetService.createTweet(createTweetRequestDTO, loggedInUser));
    }
    @PostMapping("/tweet/{tweetId}")
    public ResponseEntity<TweetResponseDTO> createQuoteTweet(@RequestBody @Valid TweetRequestDTO createTweetRequestDTO,
                                                             @PathVariable Long tweetId,
                                                            @RequestHeader String loggedInUser) {
        return ResponseEntity.ok(tweetService.createQuoteTweet(createTweetRequestDTO, tweetId, loggedInUser));
    }
}
