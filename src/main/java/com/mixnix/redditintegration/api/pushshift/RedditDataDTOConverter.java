package com.mixnix.redditintegration.api.pushshift;

import java.util.stream.Collectors;

//@Service
public class RedditDataDTOConverter {
    public static RedditResponseDTO convertToRedditResponseDTO(RedditDataDTO redditDataDTO){
        return new RedditResponseDTO(redditDataDTO.getData().stream().map(RedditDataDTOConverter::convertToString)
                .collect(Collectors.toList()));
    }

    static String convertToString(RedditSubmissionDTO redditSubmissionDTO){
        return redditSubmissionDTO.getUrl();
    }
}
