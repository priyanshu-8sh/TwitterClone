package com.example.twitter.controller;

import com.example.twitter.dto.request.ProfileRequestDTO;
import com.example.twitter.dto.request.UpdateProfileRequestDTO;
import com.example.twitter.dto.response.ProfileResponseDTO;
import com.example.twitter.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @PostMapping("/createProfile")
    public ResponseEntity<?> createProfile(@RequestBody ProfileRequestDTO profileRequestDTO )
    {

        String profileId=profileService.createProfile(profileRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(profileId);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable String id) {
        return ResponseEntity.ok(profileService.getProfile(id));
    }
    @GetMapping("/id/{email}")
    public ResponseEntity<String> getProfileIdByEmail(@PathVariable String email) {
        return ResponseEntity.ok(profileService.getProfileIdByEmail(email));
    }
    @GetMapping("/me")
    public ResponseEntity<ProfileResponseDTO> getAuthProfile(@RequestHeader String loggedInUser) {
        return ResponseEntity.ok(profileService.getAuthProfile(loggedInUser));
    }
    @GetMapping("/")
    public ResponseEntity<List<ProfileResponseDTO>> findAllByUsername(
            @RequestParam String username,
            Pageable pageable
    ) {
        List<ProfileResponseDTO> response =profileService.findAllByUsernameContaining(username, pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @Valid @RequestBody UpdateProfileRequestDTO request,
            @PathVariable String id,
            @RequestHeader String loggedInUser
    ) {
        return ResponseEntity.ok(profileService.updateProfile(id, request, loggedInUser));
    }


}
