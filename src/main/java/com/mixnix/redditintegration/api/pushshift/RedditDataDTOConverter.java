package com.mixnix.redditintegration.api.pushshift;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RedditDataDTOConverter {
    public RedditResponseDTO convertToRedditResponseDTO(RedditDataDTO redditDataDTO){
        return new RedditResponseDTO(redditDataDTO.getData().stream().map(this::convertToString)
                .collect(Collectors.toList()));
    }

    String convertToString(RedditSubmissionDTO redditSubmissionDTO){
        return redditSubmissionDTO.getUrl();
    }
}
