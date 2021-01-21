package com.mixnix.redditintegration.api.pushshift;

public interface PushshiftService {
    RedditResponseDTO findByQuery(String queryString, int pageSize);
    RedditResponseDTO downloadFromReddit(String subreddit, int pageSize);
}
