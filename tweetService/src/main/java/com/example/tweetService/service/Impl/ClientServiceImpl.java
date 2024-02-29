package com.example.tweetService.service.Impl;

import com.example.clients.profile.ProfileServiceClient;
import com.example.clients.profile.dto.response.ProfileResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.example.tweetService.service.ClientService;
@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ProfileServiceClient profileServiceClient;
    @Override
    public String getProfileIdByEmail(String loggedInUser)
    {
        return profileServiceClient.getProfileIdByLoggedInUser(loggedInUser);
    }
    @Override
    public ProfileResponseDTO getProfileById(String profileId)
    {
        return profileServiceClient.getProfileById(profileId);
    }
}
