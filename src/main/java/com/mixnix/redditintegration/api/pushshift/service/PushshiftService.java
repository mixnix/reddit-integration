package com.mixnix.redditintegration.api.pushshift.service;

import com.mixnix.redditintegration.api.pushshift.domain.UrlResponseDTO;

import java.util.List;

public interface PushshiftService {
    List<UrlResponseDTO> findByQuery(String queryString, int pageSize);
    List<UrlResponseDTO> downloadFromReddit(String subreddit, int pageSize);
}
