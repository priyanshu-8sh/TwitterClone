package com.example.twitter.service.impl;


import com.example.twitter.dao.ProfileDao;
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
    @Autowired
    ProfileDao profileDao;

    @Override
    public String createProfile(ProfileRequestDTO profileRequestDTO)
    {
        Profile profile=ProfileMapper.toEntity(profileRequestDTO);
        profileDao.createProfile(profile);
        return profile.getId();

    }
    @Override
    public ProfileResponseDTO getProfile(String id)
    {
        Profile profile=profileDao.getProfile(id);
        ProfileResponseDTO profileResponseDTO=ProfileMapper.toResponse(profile);
        return profileResponseDTO;
    }
    @Override
    public ProfileResponseDTO getAuthProfile(String loggedInUser)
    {
        Profile profile=profileDao.getProfileByEmail(loggedInUser);
        ProfileResponseDTO profileResponseDTO=ProfileMapper.toResponse(profile);
        return profileResponseDTO;
    }
    @Override
    public String getProfileIdByEmail(String email)
    {
        Profile profile=profileDao.getProfileByEmail(email);
        return profile.getId();
    }
    @Override
    public ProfileResponseDTO updateProfile(String id, UpdateProfileRequestDTO request, String loggedInUser)
    {

        return profileRepository.findById(id)
                .filter(profile -> checkUpdateAvailabilityForUser(profile.getEmail(), loggedInUser))
                .map(profile->ProfileMapper.updateProfileFromUpdateProfileRequest(request,profile))
                .map(profile->profileDao.updateProfile(profile))
                .map(profile->ProfileMapper.toResponse(profile))
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public List<ProfileResponseDTO> findAllByUsernameContaining(String username, Pageable pageable) {

        Page<Profile> profilePages=profileDao.getAllUsernameContaining(username, pageable);
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
