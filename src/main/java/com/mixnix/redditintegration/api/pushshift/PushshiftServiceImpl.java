package com.mixnix.redditintegration.api.pushshift;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class PushshiftServiceImpl implements PushshiftService{

    private final RestTemplate restTemplate;

    public RedditDataDTO findByQuery(String queryString) {
        String subredditSubmissionWithQuery = "https://api.pushshift.io/reddit/search/submission";
        return restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                queryString);
    }
}
