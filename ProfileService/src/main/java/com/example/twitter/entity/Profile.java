package com.example.twitter.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Document(collection = "profiles")
public class Profile {
    @Id
    private String id;
    @Indexed(unique = true) private String email;
    @Indexed(unique = true)
    private String username;
    private LocalDate joinDate;
    private String bio;
    private String location;
    private String website;
    private LocalDate birthDate;
    private String avatarUrl;
    private String bannerUrl;

}