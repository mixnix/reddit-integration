package com.mixnix.redditintegration.domain.memes;

import com.mixnix.redditintegration.api.pushshift.PushshiftService;
import com.mixnix.redditintegration.api.pushshift.RedditResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemesDownloadService {

    private final MemeRepository memeRepository;

    private final PushshiftService pushshiftService;

    public RedditResponseDTO saveMemesToDatabase(String subreddit, int pageSize){
        RedditResponseDTO redditResponseDTO = pushshiftService.downloadFromReddit(subreddit, pageSize);

        redditResponseDTO.setUrls(redditResponseDTO.getUrls().stream()
                .filter(this::isUrlImage).collect(Collectors.toList()));

        redditResponseDTO.getUrls().forEach(e -> memeRepository.save(new Meme(e)));

        return redditResponseDTO;
        //todo: add checking if urls are images
        //todo: add saving to database
        //todo: when the scheduler will be executing this task i need to add another table in database that will save
            //todo: timestamp
    }

    private boolean isUrlImage(String url) {
        String[] imageEndings = { ".jpeg", ".JPEG", ".png", ".PNG", ".jpg", ".JPG" };
        return Arrays.stream(imageEndings).anyMatch(url::endsWith);
    }
}
