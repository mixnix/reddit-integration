package com.mixnix.redditintegration.api.pushshift;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class PushshiftServiceImpl implements PushshiftService{

    @Value("${resource.submissions}/?size=100&q={query}")
    private String subredditSubmissionWithQuery;

    private final RestTemplate restTemplate;

    public RedditDataDTO findByQuery(String queryString) {
        return restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                queryString);
    }
}
