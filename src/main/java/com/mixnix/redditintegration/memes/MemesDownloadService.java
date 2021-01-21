package com.mixnix.redditintegration.memes;

import com.mixnix.redditintegration.api.pushshift.PushshiftService;
import com.mixnix.redditintegration.api.pushshift.RedditResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemesDownloadService {

    private final MemeRepository memeRepository;

    private final PushshiftService pushshiftService;

    public RedditResponseDTO saveMemesToDatabase(String subreddit, int pageSize){
        return pushshiftService.downloadFromReddit(subreddit, pageSize);
        //todo: add checking if urls are images
        //todo: add saving to database
    }


}
