package com.example.tweetService.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileResponseDTO {
    private String profileId;
    private String username;
    private String email;
    private Integer followers;
    private Integer followees;

    private LocalDate joinDate;

    private String bio;
    private String location;
    private String website;

    private LocalDate birthDate;
    private String avatarUrl;
    private String bannerUrl;
}
