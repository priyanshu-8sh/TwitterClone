package com.example.tweetService.repository;

import com.example.tweetService.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViewRepository extends JpaRepository<View, Long> {
    Optional<View> findByParentTweetIdAndProfileId(Long tweetId, String profileId);

}
