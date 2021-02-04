package com.mixnix.redditintegration.api.pushshift.domain;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UrlsResponseDTO {
    List<String> urls;
}
