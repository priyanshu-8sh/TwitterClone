package com.example.tweetService.repository;

import com.example.tweetService.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    int countAllByReplyToId(Long replyToId);

    int countAllByRetweetToId(Long retweetToId);

    boolean existsByRetweetToIdAndProfileId(Long retweetToId, String loggedInUser);

    Optional<Tweet> findByIdAndReplyToIsNotNull(Long replyToId);

    List<Tweet> findAllByProfileIdAndReplyToIdIsNotNull(String profileId);


}
