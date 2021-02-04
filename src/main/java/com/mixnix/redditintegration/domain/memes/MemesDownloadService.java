package com.mixnix.redditintegration.domain.memes;

import com.mixnix.redditintegration.api.pushshift.service.PushshiftService;
import com.mixnix.redditintegration.api.pushshift.domain.UrlResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemesDownloadService {

    private final MemeRepository memeRepository;

    private final PushshiftService pushshiftService;

    public List<UrlResponseDTO> saveMemesToDatabase(String subreddit, int pageSize){
        List<UrlResponseDTO> urlResponseDTOList = pushshiftService.downloadFromReddit(subreddit, pageSize);

        urlResponseDTOList
                .stream()
                .filter(e -> isUrlImage(e.getUrl()))
                .forEach(e -> memeRepository.save(new Meme(e.getUrl())));


        return urlResponseDTOList;
        //todo: add checking if urls are images
        //todo: add saving to database
        //todo: when the scheduler will be executing this task i need to add another table in database that will save
            //todo: timestamp
    }

    public UrlResponseDTO saveMemesToDatabaseBeforeGivenTime(String subreddit, Long utcTimestamp){
        //todo: make it work
//        UrlResponseDTO urlResponseDTO = pushshiftService.downloadFromRedditBeforeTime(subreddit, utcTimestamp);
        return null;
        //return timestamp of oldest meme
    }

    private boolean isUrlImage(String url) {
        String[] imageEndings = { ".jpeg", ".JPEG", ".png", ".PNG", ".jpg", ".JPG" };
        //todo: change to List.of
        //instead of list of ending you should use regex
        //change url to lowercase because comparing endings is very cpu heavy
        return Arrays.stream(imageEndings).anyMatch(url::endsWith);
    }
}
