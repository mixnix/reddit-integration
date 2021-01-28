package com.mixnix.redditintegration.api.pushshift;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RedditResponseDTO {
    List<String> urls;
}
