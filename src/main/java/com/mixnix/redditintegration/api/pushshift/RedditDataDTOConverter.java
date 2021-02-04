package com.mixnix.redditintegration.api.pushshift;

import com.mixnix.redditintegration.api.pushshift.domain.UrlResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RedditDataDTOConverter {
    public static List<UrlResponseDTO> convertToRedditResponseDTO(RedditDataDTO redditDataDTO){
        return redditDataDTO
                .getData()
                .stream()
                .map(e -> new UrlResponseDTO(e.getUrl(), e.getCreatedUtc()))
                .collect(Collectors.toList());
    }
}
