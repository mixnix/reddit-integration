package com.mixnix.redditintegration.api.pushshift;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PushshiftServiceImpl implements PushshiftService{

    private final RestTemplate restTemplate;

    private final RedditDataDTOConverter redditDataDTOConverter;

    public RedditResponseDTO findByQuery(String queryString, int pageSize) {
        // query string can be anything because it searches by title and post content
        String subredditSubmissionWithQuery = "https://api.pushshift.io/reddit/search/submission/?size={pageSize}" +
                "&q={queryString}";

        RedditDataDTO redditDataDTO = restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                pageSize, queryString);
        return redditDataDTOConverter.convertToRedditResponseDTO(redditDataDTO);
    }
}
