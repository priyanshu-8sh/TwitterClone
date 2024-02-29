package com.example.clients.profile;

import com.example.clients.profile.dto.response.ProfileResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "profile-service", path = "/api/v1/profiles/")
public interface ProfileServiceClient {
    @GetMapping("/id/{email}")
    String getProfileIdByLoggedInUser(@PathVariable String email);

    @GetMapping("{id}")
    ProfileResponseDTO getProfileById(@PathVariable String id);

    @GetMapping("/followers")
    List<ProfileResponseDTO> getFollowers(@PathVariable String profileId);
}
