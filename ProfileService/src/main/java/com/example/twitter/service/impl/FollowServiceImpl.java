package com.example.twitter.service.impl;

import com.example.twitter.dto.response.ProfileResponseDTO;
import com.example.twitter.repository.FollowRespository;
import com.example.twitter.repository.ProfileRepository;
import com.example.twitter.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.twitter.mapper.ProfileMapper;
import com.example.twitter.entity.Follow;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private FollowRespository followRespository;
    @Override
    public Boolean isFollowed(String followeeId, String loggedInUser) {
        return profileRepository.findByEmail(loggedInUser)
               .map(profile->profile.getId())
               .map(followerId->followRespository.existsByFollowerProfile_IdAndFolloweeProfile_Id(followerId, followeeId))
                .orElse(false);

    }
    @Override
    public List<ProfileResponseDTO> getFollowers(String profileId){
        return followRespository.findAllByFolloweeProfile_Id(profileId)
                .stream()
                .map(follow->follow.getFollowerProfile())
                .map(profile->ProfileMapper.toResponse(profile))
                .toList();
    }

    @Override
    public List<ProfileResponseDTO> getFollowees(String profileId)
    {
        return followRespository.findAllByFollowerProfile_Id(profileId)
                .stream()
                .map(follow->follow.getFolloweeProfile())
                .map(profile->ProfileMapper.toResponse(profile))
                .toList();
    }

    @Override
    public Boolean follow(String followeeId, String loggedInUser) {

        return profileRepository.findByEmail(loggedInUser)
                .map(profile -> profile.getId())
                .filter(followerId -> !isFollowed(followeeId, loggedInUser))
                .map(followerId -> Follow.builder()
                        .followerProfile(profileRepository.findById(followerId).orElseThrow())
                        .followeeProfile(profileRepository.findById(followeeId).orElseThrow())
                        .followDateTime(LocalDateTime.now())
                        .build())
                .map(follow -> followRespository.save(follow))
                .isPresent();
    }
    @Override
    public Boolean unfollow(String followeeId, String loggedInUser){
        return profileRepository.findByEmail(loggedInUser)
                .map(profile -> profile.getId())
                .filter(followerId -> isFollowed(followeeId, loggedInUser))
                .map(followerId->followRespository.deleteByFollowerProfile_IdAndFolloweeProfile_Id(followerId, followeeId))
                .isPresent();
    }
}
