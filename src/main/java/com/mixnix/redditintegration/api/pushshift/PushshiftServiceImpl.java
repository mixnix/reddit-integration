package com.mixnix.redditintegration.api.pushshift;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PushshiftServiceImpl implements PushshiftService{

    private final RestTemplate restTemplate;

    private final RedditDataDTOConverter redditDataDTOConverter;

    public PushshiftServiceImpl(RestTemplateBuilder restTemplateBuilder, RedditDataDTOConverter redditDataDTOConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.redditDataDTOConverter = redditDataDTOConverter;
    }

    public RedditResponseDTO findByQuery(String queryString, int pageSize) {
        // query string can be anything because it searches by title and post content
        String subredditSubmissionWithQuery = "https://api.pushshift.io/reddit/search/submission/?size={pageSize}" +
                "&q={queryString}";

        RedditDataDTO redditDataDTO = restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                pageSize, queryString);
        return redditDataDTOConverter.convertToRedditResponseDTO(redditDataDTO);
    }

    public RedditResponseDTO downloadFromReddit(String subreddit, int pageSize) {
        // query string can be anything because it searches by title and post content
        String subredditSubmissionWithQuery = "https://api.pushshift.io/reddit/search/submission/?size={pageSize}" +
                "&subreddit={subreddit}";

        RedditDataDTO redditDataDTO = restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                pageSize, subreddit);
        return redditDataDTOConverter.convertToRedditResponseDTO(redditDataDTO);
    }
}
