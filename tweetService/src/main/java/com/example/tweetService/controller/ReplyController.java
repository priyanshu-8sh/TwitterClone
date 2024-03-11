package com.example.tweetService.controller;

import com.example.tweetService.dto.request.TweetRequestDTO;
import com.example.tweetService.dto.response.TweetResponseDTO;
import com.example.tweetService.service.ReplyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping("/{replyToId}")
    public ResponseEntity<TweetResponseDTO> reply(@RequestBody @Valid TweetRequestDTO createTweetRequestDTO,
                                                  @PathVariable Long replyToId,
                                                  @RequestHeader String loggedInUser) {
        return ResponseEntity.ok(replyService.reply(createTweetRequestDTO, replyToId, loggedInUser));
    }

    @GetMapping("/replies/user/{profileId}")
    public ResponseEntity<List<TweetResponseDTO>> getAllRepliesForUser(@PathVariable String profileId)
    {
        return ResponseEntity.ok(replyService.getAllRepliesForUser(profileId));
    }
    @GetMapping("/replies/tweet/{replyToId}")
    public ResponseEntity<List<TweetResponseDTO>> getAllRepliesForUser(@PathVariable Long replyToId, @RequestHeader String loggedInUser)
    {
        return ResponseEntity.ok(replyService.getAllRepliesForTweet(replyToId, loggedInUser));
    }
    @GetMapping("/reply/{replyId}")
    public ResponseEntity<TweetResponseDTO> getReply(@PathVariable Long replyId, @RequestHeader String loggedInUser) {
        return ResponseEntity.ok(replyService.getReply(replyId, loggedInUser));
    }
    @PatchMapping("/reply/{replyId}")
    public ResponseEntity<TweetResponseDTO> updateReply(
            @Valid @RequestPart TweetRequestDTO request,
            @PathVariable Long replyId,
            @RequestHeader String loggedInUser
    ) {
        return ResponseEntity.ok(replyService.updateReply(replyId, request, loggedInUser));
    }
    @DeleteMapping("/reply/{replyId}")
    public ResponseEntity<Boolean> deleteReply(@PathVariable Long replyId, @RequestHeader String loggedInUser) {
        return ResponseEntity.ok(replyService.deleteReply(replyId, loggedInUser));
    }
}
