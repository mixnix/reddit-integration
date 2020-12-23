package com.mixnix.redditintegration.api.pushshift;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PushshiftServiceImpl implements PushshiftService{

    private final RestTemplate restTemplate;

    public RedditResponseDTO findByQuery(String queryString) {
        String subredditSubmissionWithQuery = "https://api.pushshift.io/reddit/search/submission";

        RedditDataDTO redditDataDTO = restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                queryString);
        return new RedditResponseDTO(redditDataDTO.getData().stream().map(RedditSubmissionDTO::getUrl).collect(Collectors.toList()));
    }
}
