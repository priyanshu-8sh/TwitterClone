package com.example.twitter.controller;

import com.example.twitter.dto.response.ProfileResponseDTO;
import com.example.twitter.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follows")
public class FollowController {

    private FollowService followService;
    @GetMapping("/{followeeId}")
    public ResponseEntity<Boolean> isFollowed(@PathVariable String followeeId, @RequestHeader String loggedInUser) {
        return ResponseEntity.ok(followService.isFollowed(followeeId, loggedInUser));
    }
    @GetMapping("/{profileId}/followers")
    public ResponseEntity<List<ProfileResponseDTO>> getFollowers(@PathVariable String profileId) {
        return ResponseEntity.ok(followService.getFollowers(profileId));
    }
    @GetMapping("/{profileId}/followees")
    public ResponseEntity<List<ProfileResponseDTO>> getFollowees(@PathVariable String profileId) {
        return ResponseEntity.ok(followService.getFollowees(profileId));
    }
    @PostMapping("/{followeeId}")
    public ResponseEntity<Boolean> follow(@PathVariable String followeeId, @RequestHeader String loggedInUser) {
        return ResponseEntity.ok(followService.follow(followeeId, loggedInUser));
    }

    @DeleteMapping("/{followeeId}")
    public ResponseEntity<Boolean> unfollow(@PathVariable String followeeId, @RequestHeader String loggedInUser) {
        return ResponseEntity.ok(followService.unfollow(followeeId, loggedInUser));
    }
}
