package com.mixnix.redditintegration.api.pushshift;

public interface PushshiftService {
    RedditResponseDTO findByQuery(String queryString);
}
