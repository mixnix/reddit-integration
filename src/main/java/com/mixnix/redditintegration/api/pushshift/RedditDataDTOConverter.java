package com.mixnix.redditintegration.api.pushshift;

import com.mixnix.redditintegration.api.pushshift.domain.UrlsResponseDTO;

import java.util.stream.Collectors;

//@Service
public class RedditDataDTOConverter {
    public static UrlsResponseDTO convertToRedditResponseDTO(RedditDataDTO redditDataDTO){
        return new UrlsResponseDTO(redditDataDTO.getData().stream().map(RedditDataDTOConverter::convertToString)
                .collect(Collectors.toList()));
    }

    private static String convertToString(RedditSubmissionDTO redditSubmissionDTO){
        return redditSubmissionDTO.getUrl();
    }
}
