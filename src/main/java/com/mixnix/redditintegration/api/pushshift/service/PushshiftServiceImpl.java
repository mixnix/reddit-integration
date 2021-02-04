package com.mixnix.redditintegration.api.pushshift.service;

import com.mixnix.redditintegration.api.pushshift.RedditDataDTO;
import com.mixnix.redditintegration.api.pushshift.RedditDataDTOConverter;
import com.mixnix.redditintegration.api.pushshift.domain.UrlResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PushshiftServiceImpl implements PushshiftService{

    private final RestTemplate restTemplate;

    public List<UrlResponseDTO> findByQuery(String queryString, int pageSize) {
        // query string can be anything because it searches by title and post content
        String subredditSubmissionWithQuery = "https://api.pushshift.io/reddit/search/submission/?size={pageSize}" +
                "&q={queryString}";

        RedditDataDTO redditDataDTO = restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                pageSize, queryString);
        return RedditDataDTOConverter.convertToRedditResponseDTO(redditDataDTO);
    }

    public List<UrlResponseDTO> downloadFromReddit(String subreddit, int pageSize) {
        // query string can be anything because it searches by title and post content
        String subredditSubmissionWithQuery = "https://api.pushshift.io/reddit/search/submission/?size={pageSize}" +
                "&subreddit={subreddit}";

        RedditDataDTO redditDataDTO = restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                pageSize, subreddit);
        return RedditDataDTOConverter.convertToRedditResponseDTO(redditDataDTO);
    }
}
