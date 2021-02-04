package com.mixnix.redditintegration.api.pushshift.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UrlResponseDTO {
    private String url;
    private Long createdUtc;
}
