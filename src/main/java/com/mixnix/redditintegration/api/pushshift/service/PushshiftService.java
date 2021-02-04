package com.mixnix.redditintegration.api.pushshift.service;

import com.mixnix.redditintegration.api.pushshift.domain.UrlsResponseDTO;

public interface PushshiftService {
    UrlsResponseDTO findByQuery(String queryString, int pageSize);
    UrlsResponseDTO downloadFromReddit(String subreddit, int pageSize);
}
