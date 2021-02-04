package com.mixnix.redditintegration.api.pushshift.service;

import com.mixnix.redditintegration.api.pushshift.RedditDataDTO;
import com.mixnix.redditintegration.api.pushshift.RedditDataDTOConverter;
import com.mixnix.redditintegration.api.pushshift.domain.UrlsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PushshiftServiceImpl implements PushshiftService{

    private final RestTemplate restTemplate;

    public UrlsResponseDTO findByQuery(String queryString, int pageSize) {
        // query string can be anything because it searches by title and post content
        String subredditSubmissionWithQuery = "https://api.pushshift.io/reddit/search/submission/?size={pageSize}" +
                "&q={queryString}";

        RedditDataDTO redditDataDTO = restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                pageSize, queryString);
        return RedditDataDTOConverter.convertToRedditResponseDTO(redditDataDTO);
    }

    public UrlsResponseDTO downloadFromReddit(String subreddit, int pageSize) {
        // query string can be anything because it searches by title and post content
        String subredditSubmissionWithQuery = "https://api.pushshift.io/reddit/search/submission/?size={pageSize}" +
                "&subreddit={subreddit}";

        RedditDataDTO redditDataDTO = restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                pageSize, subreddit);
        return RedditDataDTOConverter.convertToRedditResponseDTO(redditDataDTO);
    }
}
