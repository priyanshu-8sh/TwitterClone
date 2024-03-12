package com.example.twitter.dao.impl;

import com.example.twitter.dao.ProfileDao;
import com.example.twitter.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.twitter.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileDaoImpl implements ProfileDao {
    @Autowired
    ProfileRepository profileRepository;
    @Override
    public Profile createProfile(Profile profile)
    {
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public Profile getProfile(String id)
    {
        Optional<Profile> profileDb=profileRepository.findById(id);
        if(profileDb.isPresent())
        {
            return profileDb.get();
        }
        else
        {
            throw new RuntimeException("Profile not found");
        }
    }
    @Override
    public Profile getProfileByEmail(String email)
    {
        Optional<Profile> profileDb=profileRepository.findByEmail(email);
        if(profileDb.isPresent())
        {
            return profileDb.get();
        }
        else
        {
            throw new RuntimeException("Profile not found");
        }
    }

    @Override
    public Profile updateProfile(Profile profile)
    {
        profileRepository.save(profile);
        return profile;
    }
    @Override
    public Page<Profile> getAllUsernameContaining(String username, Pageable pageable)
    {
        return profileRepository.findByUsernameContaining(username,pageable);
    }
}
