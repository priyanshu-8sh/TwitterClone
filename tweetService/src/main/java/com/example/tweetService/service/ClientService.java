package com.example.tweetService.service;

import com.example.clients.profile.dto.response.ProfileResponseDTO;


public interface ClientService {
    String getProfileIdByEmail(String loggedInUser);
    ProfileResponseDTO getProfileById(String profileId);
}
