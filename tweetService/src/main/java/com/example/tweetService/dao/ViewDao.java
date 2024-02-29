package com.example.tweetService.dao;

import com.example.tweetService.entity.View;
import org.springframework.stereotype.Service;

@Service
public interface ViewDao {
     View findByTweetIdAndProfileId(long tweetId, String profileId);
     View createView(View view);
}
