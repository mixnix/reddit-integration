package com.mixnix.redditintegration.api.reddit;

import com.mixnix.redditintegration.api.pushshift.RedditDataDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@Slf4j
public class RedditServiceImpl {

    @Value("${resource.reddit.authorization")
    private String authorizationResource;

    @Value("${resource.submissions}/?size=100&q={query}")
    private String subredditSubmissionWithQuery;

    private final RestTemplate restTemplate;

    public RedditServiceImpl() {
        restTemplate = new RestTemplate();

//        restTemplate.postForObject()
//
//        restTemplate.getInterceptors().add(getBearerTokenInterceptor(accessToken));
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
                request.getHeaders().add("Authorization", "Bearer " + accessToken);
                return execution.execute(request, bytes);
            }
        };
    }

    public RedditDataDTO findByQuery(String queryString) {
        return restTemplate.getForObject(subredditSubmissionWithQuery, RedditDataDTO.class,
                queryString);
    }
}
