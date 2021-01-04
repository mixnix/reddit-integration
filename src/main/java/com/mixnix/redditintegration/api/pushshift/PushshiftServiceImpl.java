package com.mixnix.redditintegration.api.pushshift;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PushshiftServiceImpl implements PushshiftService{

    private final RestTemplate restTemplate;

    public RedditResponseDTO findByQuery(String queryString) {
        // query string can be anything because it searches by title and post content
        String subredditSubmissionWithQuery = "https://api.pushshift.io/reddit/search/submission/?size=100" +
                "&q={queryString}";

        RedditDataDTO redditDataDTO = restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                queryString);
        return new RedditResponseDTO(redditDataDTO.getData().stream().map(RedditSubmissionDTO::getUrl)
                .collect(Collectors.toList()));
    }
}
