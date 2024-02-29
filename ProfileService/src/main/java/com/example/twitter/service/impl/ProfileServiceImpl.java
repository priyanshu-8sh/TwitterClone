package com.example.twitter.service.impl;


import com.example.twitter.dto.request.ProfileRequestDTO;
import com.example.twitter.dto.request.UpdateProfileRequestDTO;
import com.example.twitter.dto.response.ProfileResponseDTO;
import com.example.twitter.entity.Profile;
import com.example.twitter.mapper.ProfileMapper;
import com.example.twitter.repository.ProfileRepository;
import com.example.twitter.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Override
    public String createProfile(ProfileRequestDTO profileRequestDTO)
    {
        return Optional.of(profileRequestDTO)
                .map(ProfileMapper::toEntity)
                .map(profileRepository::save)
                .map(profile -> profile.getId())
                .orElseThrow(() -> new RuntimeException("Error while creating profile"));

    }
    @Override
    public ProfileResponseDTO getProfile(String id)
    {
        return profileRepository.findById(id)
                .map(profile->ProfileMapper.toResponse(profile))
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
    @Override
    public ProfileResponseDTO getAuthProfile(String loggedInUser)
    {
        return profileRepository.findByEmail(loggedInUser)
                .map(profile->ProfileMapper.toResponse(profile))
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }


    @Override
    public String getProfileIdByEmail(String email)
    {
        return profileRepository.findByEmail(email)
                .map(profile->profile.getId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
    @Override
    public ProfileResponseDTO updateProfile(String id, UpdateProfileRequestDTO request, String loggedInUser)
    {
        return profileRepository.findById(id)
                .filter(profile -> checkUpdateAvailabilityForUser(profile.getEmail(), loggedInUser))
                .map(profile->ProfileMapper.updateProfileFromUpdateProfileRequest(request,profile))
                .map(profileRepository::save)
                .map(profile->ProfileMapper.toResponse(profile))
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }



    public List<ProfileResponseDTO> findAllByUsernameContaining(String username, Pageable pageable) {
        Page<Profile> profilePages=profileRepository.findByUsernameContaining(username, pageable);
        List<Profile> profiles=profilePages.getContent();
        return profiles.stream()
                .map(profile->ProfileMapper.toResponse(profile))
                .collect(Collectors.toList());
    }

    private boolean checkUpdateAvailabilityForUser(String updatingUser, String loggedInUser) {
        if (!updatingUser.equals(loggedInUser)) {
            throw new RuntimeException(
                    "error.forbidden"
            );
        }
        return true;
    }
}
