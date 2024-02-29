package com.example.twitter.dto.response;

import java.util.List;

public class PageProfileResponse {
    List<ProfileResponseDTO> content;
    Integer pageNumber;
    Integer pageSize;
    int totalPages;
    int totalElements;
    boolean last;
}
