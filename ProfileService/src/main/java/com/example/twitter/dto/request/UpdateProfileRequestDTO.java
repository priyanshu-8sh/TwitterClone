package com.example.twitter.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UpdateProfileRequestDTO {
    @NotNull(message = "{username.not-null}")
    @Size(min = 5, max = 50, message = "{username.size}")
    private String username;

    @Size(max = 160, message = "{bio.size}")
    private String bio;
    @Size(max = 30, message = "{location.size}")
    private String location;
    @Size(max = 100, message = "{website.size}")
    private String website;

    @Past
    private LocalDate birthDate;
}

