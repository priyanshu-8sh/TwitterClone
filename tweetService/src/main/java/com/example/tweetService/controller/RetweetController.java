package com.example.tweetService.controller;

import com.example.tweetService.dto.response.TweetResponseDTO;
import com.example.tweetService.service.RetweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/retweet")
public class RetweetController {
    @Autowired
    private RetweetService retweetService;
    @PostMapping("/{tweetId}")
    public ResponseEntity<Boolean> retweet(@PathVariable Long tweetId, @RequestHeader String loggedInUser) {
        return ResponseEntity.ok(retweetService.retweet(tweetId, loggedInUser));
    }
    @DeleteMapping("/delete/{tweetId}")
    public ResponseEntity<Boolean> deleteRetweet(@PathVariable Long tweetId, @RequestHeader String loggedInUser) {
        return ResponseEntity.ok(retweetService.deleteRetweet(tweetId, loggedInUser));
    }
    @GetMapping("/retweet/{retweetId}")
    public ResponseEntity<TweetResponseDTO> getRetweet(@PathVariable Long retweetId, @RequestHeader String loggedInUser) {
        return ResponseEntity.ok(retweetService.getRetweetById(retweetId, loggedInUser));
    }
    @GetMapping("/retweets/user/{profileId}")
    public ResponseEntity<List<TweetResponseDTO>> getAllRetweetsForUser(
            @PathVariable String profileId
    ) {
        return ResponseEntity.ok(retweetService.getAllRetweetsForUser(profileId));
    }
}
