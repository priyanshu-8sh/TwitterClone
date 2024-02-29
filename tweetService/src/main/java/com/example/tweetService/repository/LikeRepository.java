package com.example.tweetService.repository;

import com.example.tweetService.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long>{
    Optional<Like> findByParentTweetIdAndProfileId(Long tweetId, String profileId);
    List<Like> findAllByParentTweetId(Long tweetId);
    int countAllByParentTweetId(Long parentTweetId);
}
