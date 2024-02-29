package com.example.twitter.service;

import com.example.twitter.dto.request.ProfileRequestDTO;
import com.example.twitter.dto.request.UpdateProfileRequestDTO;
import com.example.twitter.dto.response.ProfileResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {
    String createProfile(ProfileRequestDTO profileRequestDTO);
    ProfileResponseDTO getProfile(String id);
    ProfileResponseDTO getAuthProfile(String loggedInUser);
    String getProfileIdByEmail(String email);
    ProfileResponseDTO updateProfile(String id, UpdateProfileRequestDTO request, String loggedInUser);
    List<ProfileResponseDTO> findAllByUsernameContaining(String username, Pageable pageable);
}
