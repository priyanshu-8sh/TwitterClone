package com.example.tweetService.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TweetRequestDTO {
    @NotEmpty(message="{text.not_empty}")
    private String text;
}
