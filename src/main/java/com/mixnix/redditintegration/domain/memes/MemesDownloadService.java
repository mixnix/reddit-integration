package com.mixnix.redditintegration.domain.memes;

import com.mixnix.redditintegration.api.pushshift.service.PushshiftService;
import com.mixnix.redditintegration.api.pushshift.domain.UrlsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemesDownloadService {

    private final MemeRepository memeRepository;

    private final PushshiftService pushshiftService;

    public UrlsResponseDTO saveMemesToDatabase(String subreddit, int pageSize){
        UrlsResponseDTO urlsResponseDTO = pushshiftService.downloadFromReddit(subreddit, pageSize);

        urlsResponseDTO.setUrls(urlsResponseDTO.getUrls().stream()
                .filter(this::isUrlImage).collect(Collectors.toList()));

        urlsResponseDTO.getUrls().forEach(e -> memeRepository.save(new Meme(e)));

        return urlsResponseDTO;
        //todo: add checking if urls are images
        //todo: add saving to database
        //todo: when the scheduler will be executing this task i need to add another table in database that will save
            //todo: timestamp
    }

    private boolean isUrlImage(String url) {
        String[] imageEndings = { ".jpeg", ".JPEG", ".png", ".PNG", ".jpg", ".JPG" };
        //todo: change to List.of
        //instead of list of ending you should use regex
        //change url to lowercase because comparing endings is very cpu heavy
        return Arrays.stream(imageEndings).anyMatch(url::endsWith);
    }
}
