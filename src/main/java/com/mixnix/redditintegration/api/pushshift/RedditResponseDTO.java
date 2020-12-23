package com.mixnix.redditintegration.api.pushshift;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RedditResponseDTO {
    //maybe there should be 2 DTO objects
    List<String> urls;
}
