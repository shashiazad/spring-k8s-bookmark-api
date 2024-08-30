package com.shashilabs.bookmarker.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class CreateBookmarkRequest {
    @NotEmpty(message = "Title should not be empty!")
    private String title;
    @NotEmpty(message = "URL should not be empty!")
    private String url;
}
