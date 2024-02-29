package com.example.twitter.service;

import com.example.twitter.dto.response.ProfileResponseDTO;

import java.util.List;

public interface FollowService {
    Boolean isFollowed(String followeeId, String loggedInUser);

    List<ProfileResponseDTO> getFollowers(String profileId);

    List<ProfileResponseDTO> getFollowees(String profileId);

    Boolean follow(String followeeId, String loggedInUser);

    Boolean unfollow(String followeeId, String loggedInUser);
}