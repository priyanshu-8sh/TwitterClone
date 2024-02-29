package com.example.tweetService.dto.response;

import com.example.clients.profile.dto.response.ProfileResponseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class TweetResponseDTO {
    private Long id;
    private TweetResponseDTO replyTo;
    private TweetResponseDTO quoteTo;
    private TweetResponseDTO retweetTo;
    private ProfileResponseDTO profile;
    private String text;
    private Set<String> mediaUrls;
    private Integer retweets;
    private Integer replies;
    private Integer likes;
    private Integer views;
    private LocalDateTime creationDate;
    private Boolean isRetweeted;
    private Boolean isLiked;
    //whether the tweet belongs to the currently logged-in user or not.
    private Boolean isBelongs;
}
