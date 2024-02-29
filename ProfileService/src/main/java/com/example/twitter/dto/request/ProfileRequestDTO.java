package com.example.twitter.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ProfileRequestDTO {
    @NotEmpty(message = "{email.not-null}")
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message = "{username.not-null}")
    @Size(min = 5, max = 50, message = "{username.size}")
    private String username;
    @NotEmpty(message = "{joinDate.not-null}")
    private LocalDate localDate;
}
