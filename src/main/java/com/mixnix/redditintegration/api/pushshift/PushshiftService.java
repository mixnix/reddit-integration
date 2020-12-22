package com.mixnix.redditintegration.api.pushshift;

public interface PushshiftService {
    RedditDataDTO findByQuery(String queryString);
}
