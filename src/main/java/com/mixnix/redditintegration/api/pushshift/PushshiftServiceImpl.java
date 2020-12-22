package com.mixnix.redditintegration.api.pushshift;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PushshiftServiceImpl {

    @Value("${resource.submissions}/?size=100&q={query}")
    private String subredditSubmissionWithQuery;

    @Autowired
    private RestTemplate restTemplate;

    public RedditDataDTO findByQuery(String queryString) {
        return restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                queryString);
    }
}
