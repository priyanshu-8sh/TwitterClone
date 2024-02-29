package com.example.twitter.mapper;

import com.example.twitter.dto.request.ProfileRequestDTO;
import com.example.twitter.dto.request.UpdateProfileRequestDTO;
import com.example.twitter.dto.response.ProfileResponseDTO;
import com.example.twitter.entity.Profile;

public class ProfileMapper {
    public static Profile toEntity(ProfileRequestDTO profileRequestDTO) {
        if ( profileRequestDTO == null ) {
            return null;
        }
        Profile profile = new Profile();
        profile.setUsername( profileRequestDTO.getUsername());
        profile.setEmail( profileRequestDTO.getEmail() );
        profile.setJoinDate( profileRequestDTO.getLocalDate());

        return profile;
    }
    public static ProfileResponseDTO toResponse(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO();

        profileResponseDTO.setProfileId( profile.getId() );
        profileResponseDTO.setUsername( profile.getUsername() );
        profileResponseDTO.setEmail( profile.getEmail() );
        profileResponseDTO.setFollowers( 23 );
        profileResponseDTO.setFollowees( 23 );
        profileResponseDTO.setJoinDate( profile.getJoinDate() );
        profileResponseDTO.setBio( profile.getBio() );
        profileResponseDTO.setLocation( profile.getLocation() );
        profileResponseDTO.setWebsite( profile.getWebsite() );
        profileResponseDTO.setBirthDate( profile.getBirthDate() );
        profileResponseDTO.setAvatarUrl( profile.getAvatarUrl() );
        profileResponseDTO.setBannerUrl( profile.getBannerUrl() );

        return profileResponseDTO;
    }

    public static Profile updateProfileFromUpdateProfileRequest(UpdateProfileRequestDTO updateProfileRequestDTO, Profile profile) {
        if ( updateProfileRequestDTO == null ) {
            return null;
        }
        profile.setUsername( updateProfileRequestDTO.getUsername());
        profile.setBio( updateProfileRequestDTO.getBio() );
        profile.setLocation( updateProfileRequestDTO.getLocation() );
        profile.setWebsite( updateProfileRequestDTO.getWebsite() );
        profile.setBirthDate( updateProfileRequestDTO.getBirthDate() );

        return profile;
    }
}
