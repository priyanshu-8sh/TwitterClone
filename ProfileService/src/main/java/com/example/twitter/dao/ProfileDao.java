package com.example.twitter.dao;


import com.example.twitter.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileDao {
    Profile createProfile(Profile profile);
    Profile getProfile(String id);
    Profile getProfileByEmail(String email);
    Profile updateProfile(Profile profile);

    Page<Profile> getAllUsernameContaining(String username, Pageable pageable);
}
