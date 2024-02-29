package com.example.tweetService.controller;

import com.example.tweetService.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    private LikeService likeService;
    @PostMapping("/{tweetId}")
    public ResponseEntity<?> likeTweet(@PathVariable Long tweetId, @RequestHeader String loggedInUser)
    {
        likeService.likeTweet(tweetId,loggedInUser);
        return ResponseEntity.ok("Tweet liked successfully");
    }
    @DeleteMapping("/{tweetId}")
    public ResponseEntity<?> unlikeTweet(@PathVariable Long tweetId, @RequestHeader String loggedInUser)
    {
        likeService.unlikeTweet(tweetId,loggedInUser);
        return ResponseEntity.ok("Tweet Unliked successfully");
    }


}
